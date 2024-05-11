package com.sg.bjftviewprotect.controller;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.sg.bjftviewprotect.annotation.LoginVerification;
import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.entity.User;
import com.sg.bjftviewprotect.entity.UserRole;
import com.sg.bjftviewprotect.request.UserRequest;
import com.sg.bjftviewprotect.service.UserRoleService;
import com.sg.bjftviewprotect.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
@RestController
@RequestMapping("/user")
@LoginVerification
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/searchUser")
    public Result<?> searchUser(@RequestParam(value = "id",required = false) String id,
                                @RequestParam(value = "name",required = false) String name,
                                @RequestParam(value = "roleId",required = false) String roleId) {
        return userService.searchUser(id,name,roleId);
    }

    @PutMapping("/updateUser")
    public Result<?> updateUser(@RequestBody UserRequest userRequest) {
        User one = userService.getOne(new LambdaQueryWrapper<User>() {{
            eq(User::getAccount, userRequest.getAccount());
        }},false);
        if (ObjectUtils.isEmpty(one)) {
            return Result.fail("帐号重复");
        }
        User user = new User() {{
            setId(userRequest.getId());
            setName(userRequest.getName());
            setPassword(StringUtils.isBlank(userRequest.getPassword()) ? null : MD5.create().digestHex(userRequest.getPassword()));
            setAccount(userRequest.getAccount());
            setRemark(userRequest.getRemark());
            setStatus(userRequest.getStatus());
        }};
        userService.saveOrUpdate(user);
        if (!ObjectUtils.isEmpty(userRequest.getRoleId())){
            List<String> roleIdList = userRequest.getRoleId();
            List<UserRole> userRoles = new ArrayList<>();
            // 移除当前用户角色中间表信息
            userRoleService.remove(new LambdaQueryWrapper<UserRole>(){{
                eq(UserRole::getUserId, user.getId());
            }});
            for (String roleId : roleIdList){
                userRoles.add(new UserRole(){{
                    setUserId(user.getId());
                    setRoleId(roleId);
                }});
            }
            userRoleService.saveBatch(userRoles);
        }
        return Result.success("操作成功");
    }



    @DeleteMapping("/deleteUser")
    public Result<?> deleteUser(@RequestParam(value = "id") String id) {
        userService.removeById(id);
        return Result.success("删除成功");
    }

}
