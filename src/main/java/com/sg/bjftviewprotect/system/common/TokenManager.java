package com.sg.bjftviewprotect.system.common;

import com.sg.bjftviewprotect.system.constant.CommonConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author wangshuo
 * @Description
 * @Date 2024/5/9 14:07
 **/
public class TokenManager {
    // session 过期时间
    private static final long SESSION_TIMEOUT_MS = 30 * 60 * 1000; // 30分钟
    // session存储的token前缀
    private static final String TOKEN_ATTRIBUTE_PREFIX = "TOKEN_";
    // session存储的token时间前缀
    public static final String TOKEN_EXPIRY_PREFIX = "TOKEN_EXPIRY_";

    /**
     * 设置token
     */
    public static void setUserToken(HttpServletRequest request, String account, String token) {
        HttpSession session = request.getSession();
        session.setAttribute(TOKEN_ATTRIBUTE_PREFIX + account, token);
        long expiryTime = System.currentTimeMillis() + SESSION_TIMEOUT_MS;
        session.setAttribute(TOKEN_EXPIRY_PREFIX + token, expiryTime);
    }
    /**
     * 获取token
     */
    public static String getToken(HttpServletRequest request) {
        String account = getAccountFromCookies(request);
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute(TOKEN_ATTRIBUTE_PREFIX + account);
        }
        return null;
    }

    /**
     * 验证过期时间
     */
    public static boolean isTokenValid(HttpServletRequest request) {
        String account = getAccountFromCookies(request);
        if (account == null) {
            return false;
        }

        String token = getToken(request);
        if (token == null) {
            return false;
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            Long expiryTime = (Long) session.getAttribute(TOKEN_EXPIRY_PREFIX + token);
            return expiryTime != null && expiryTime >= System.currentTimeMillis();
        }
        return false;
    }

    /**
     * 刷新Token过期时间
     */
    public static void onSuccessfulRequest(HttpServletRequest request) {
        String account = getAccountFromCookies(request);
        if (account == null) {
            throw new RuntimeException("Account not found in cookies");
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            String token = getToken(request);
            if (token != null) {
                long newExpiryTime = System.currentTimeMillis() + SESSION_TIMEOUT_MS;
                session.setAttribute(TOKEN_EXPIRY_PREFIX + token, newExpiryTime);
            }
        }
    }


    /**
     * 从cookie 获取 Account
     */
    private static String getAccountFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CommonConstant.X_USER_ACCOUNT)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
