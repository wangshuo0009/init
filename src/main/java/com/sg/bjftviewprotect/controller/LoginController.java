package com.sg.bjftviewprotect.controller;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.common.TokenManager;
import com.sg.bjftviewprotect.entity.User;
import com.sg.bjftviewprotect.request.UserLoginRequest;
import com.sg.bjftviewprotect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @ClassName LoginController
 * @Author wangshuo
 * @Date 2024/5/9 13:30
 * @Version 1.0
 **/
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public Result<?> signIn(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        String account = userLoginRequest.getAccount();
        String password = userLoginRequest.getPassword();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, account)
                .eq(User::getPassword, MD5.create().digestHex(password));
        User one = userService.getOne(queryWrapper);
        if (ObjectUtils.isEmpty(one)) {
            return Result.fail("用户名密码错误");
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        TokenManager.setUserToken(request, account, token);
        return Result.success(200, "登陆成功", null, token);
    }


}
