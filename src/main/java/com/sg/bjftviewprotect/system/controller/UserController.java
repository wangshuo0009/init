package com.sg.bjftviewprotect.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.annotation.LoginVerification;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.User;
import com.sg.bjftviewprotect.system.request.UserRequest;
import com.sg.bjftviewprotect.system.service.UserService;
import com.sg.bjftviewprotect.system.util.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询用户信息
     */
    @Operation(summary = "查询用户信息")
    @PostMapping("/searchUser")
    public Result<?> searchUser(@RequestBody UserRequest userRequest,
                                //@CookieValue(value = CommonConstant.X_USER_ID) String userId) {
                                @RequestHeader(value = CommonConstant.X_USER_ID) String userId) {
        PageUtil.initPage(userRequest);
        Page<User> page = userService.searchUser(userRequest, userId);
        return Result.success("查询成功", page);
    }

    /**
     * 新增用户信息
     */
    @Operation(summary = "新增用户信息")
    @PostMapping("/saveUser")
    public Result<?> saveUser(@RequestBody UserRequest userRequest,
                              //@CookieValue(value = CommonConstant.X_USER_ID) String userId) {
                              @RequestHeader(value = CommonConstant.X_USER_ID) String userId) {
        // 参数验证
        try {
            parameterValidation(userRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        int i = userService.saveUser(userRequest, userId);
        return Result.success("操作成功",i);
    }

    /**
     * 更新用户信息
     */
    @Operation(summary = "更新用户信息")
    @PostMapping("/updateUser")
    public Result<?> updateUser(@RequestBody UserRequest userRequest) {
        // 参数验证
        try {
            parameterValidation(userRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        int i = userService.updateUser(userRequest);
        return Result.success("操作成功",i);
    }



    /**
     * 删除用户信息
     */
    @Operation(summary = "删除用户信息")
    @DeleteMapping("/deleteUser/{id}")
    public Result<?> deleteUser(@PathVariable("id") String id) {
        userService.removeById(id);
        return Result.success("删除成功");
    }

    /**
     * 参数验证
     */
    public void parameterValidation(UserRequest request){
        if (StringUtils.isBlank(request.getAccount())) {
            throw new RuntimeException("帐号不能为空");
        }
        User one = userService.getOne(new LambdaQueryWrapper<User>()
                .ne(StringUtils.isNotBlank(request.getId()), User::getId, request.getId())
                .eq(User::getAccount, request.getAccount()),false);
        if (!ObjectUtils.isEmpty(one)) {
            throw new RuntimeException("帐号重复");
        }
    }


}
