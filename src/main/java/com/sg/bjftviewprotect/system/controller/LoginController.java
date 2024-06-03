package com.sg.bjftviewprotect.system.controller;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.bjftviewprotect.system.common.CookieManager;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.common.TokenManager;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.User;
import com.sg.bjftviewprotect.system.request.UserLoginRequest;
import com.sg.bjftviewprotect.system.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName LoginController
 * @Author wangshuo
 * @Date 2024/5/9 13:30
 * @Version 1.0
 **/
@RestController
@RequestMapping("/login")
@Tag(name = "系统登陆")
public class LoginController {


    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public Result<?> signIn(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request, HttpServletResponse response) {
        String account = userLoginRequest.getAccount();
        String password = userLoginRequest.getPassword();
        User one = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getAccount, account)
                .eq(User::getPassword, MD5.create().digestHex(password))
        );
        if (ObjectUtils.isEmpty(one)) {
            return Result.fail("用户名密码错误");
        }
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        CookieManager.setCookie(request, response, account, one.getId(), token);
        TokenManager.setAccountToken(request, account, token);
        // 穿透跨域有问题，前端设置
        Map<String,String> map = new HashMap<>();
        map.put(CommonConstant.X_USER_ID, one.getId());
        map.put(CommonConstant.X_USER_ACCOUNT, one.getAccount());

        return Result.success(200, "登陆成功", map, token);
    }




}
