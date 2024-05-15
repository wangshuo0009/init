package com.sg.bjftviewprotect.controller;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sg.bjftviewprotect.annotation.LoginVerification;
import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.constant.CommonConstant;
import com.sg.bjftviewprotect.entity.User;
import com.sg.bjftviewprotect.entity.UserRole;
import com.sg.bjftviewprotect.request.UserRequest;
import com.sg.bjftviewprotect.service.RoleService;
import com.sg.bjftviewprotect.service.UserRoleService;
import com.sg.bjftviewprotect.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@Tag(name = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    /**
     * 查询用户信息
     */
    @Operation(summary = "查询用户信息")
    @PostMapping("/searchUser")
    public Result<?> searchUser(@RequestBody UserRequest userRequest,
                                @CookieValue(value = CommonConstant.X_USER_ID) String userId) {
        return userService.searchUser(userRequest, userId);
    }

    /**
     * 新增用户信息
     */
    @Operation(summary = "新增用户信息")
    @PostMapping("/saveUser")
    @Transactional(rollbackFor = Exception.class)
    public Result<?> saveUser(@RequestBody UserRequest userRequest,
                              @CookieValue(value = CommonConstant.X_USER_ID) String userId) {
        // 参数验证
        try {
            parameterValidation(userRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        User user = new User() {{
            setName(userRequest.getName());
            setPassword(StringUtils.isBlank(userRequest.getPassword()) ? null : MD5.create().digestHex(userRequest.getPassword()));
            setAccount(userRequest.getAccount());
            setRemark(userRequest.getRemark());
            setIsEnable(userRequest.getIsEnable() == null ? 1 : userRequest.getIsEnable());
            setIsDelete(CommonConstant.NOT_DELETE);
            setParentId(userId);
            setCreateTime(LocalDateTime.now());
        }};
        userService.save(user);
        if (!ObjectUtils.isEmpty(userRequest.getRoleId())){
            List<String> roleIdList = userRequest.getRoleId();
            List<UserRole> userRoles = new ArrayList<>();
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

    /**
     * 更新用户信息
     */
    @Operation(summary = "更新用户信息")
    @PostMapping("/updateUser")
    @Transactional(rollbackFor = Exception.class)
    public Result<?> updateUser(@RequestBody UserRequest userRequest) {
        // 参数验证
        try {
            parameterValidation(userRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        User user = new User() {{
            setId(userRequest.getId());
            setName(userRequest.getName());
            setPassword(StringUtils.isBlank(userRequest.getPassword()) ? null : MD5.create().digestHex(userRequest.getPassword()));
            setAccount(userRequest.getAccount());
            setRemark(userRequest.getRemark());
            setIsEnable(userRequest.getIsEnable());
        }};
        userService.updateById(user);
        if (!ObjectUtils.isEmpty(userRequest.getRoleId())){
            List<String> roleIdList = userRequest.getRoleId();
            List<UserRole> userRoles = new ArrayList<>();
            // 移除当前用户角色中间表信息
            userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
            for (String roleId : roleIdList){
                userRoles.add(new UserRole(){{
                    setUserId(user.getId());
                    setRoleId(roleId);
                }});
            }
            userRoleService.saveBatch(userRoles);
        } else {
            userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
        }
        return Result.success("操作成功");
    }



    /**
     * 删除用户信息
     */
    @Operation(summary = "删除用户信息")
    @DeleteMapping("/deleteUser")
    public Result<?> deleteUser(@RequestParam("userId") String userId) {
        userService.removeById(userId);
        return Result.success("删除成功");
    }

    /**
     * 参数验证
     */
    public void parameterValidation(UserRequest request){
        if (StringUtils.isBlank(request.getAccount())) {
            throw new RuntimeException("帐号不能为空");
        }
        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getAccount, request.getAccount()),false);
        if (!ObjectUtils.isEmpty(one)) {
            throw new RuntimeException("帐号重复");
        }
    }


}
