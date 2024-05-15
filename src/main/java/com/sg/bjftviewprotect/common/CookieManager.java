package com.sg.bjftviewprotect.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName CookieManager
 * @Author wangshuo
 * @Date 2024/5/15 11:25
 * @Version 1.0
 **/
public class CookieManager {
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String account, String userId) {
        // 创建一个 Cookie 存储用户帐号
        Cookie accountCookie = new Cookie("X-User-Account", account);
        // 设置 Cookie 的路径，可以根据需要进行调整
        accountCookie.setPath("/");
        // 将 Cookie 添加到响应中
        response.addCookie(accountCookie);

        // 创建一个 Cookie 存储用户ID
        Cookie idCookie = new Cookie("X-User-ID", userId);
        idCookie.setPath("/");
        response.addCookie(idCookie);
    }
}
