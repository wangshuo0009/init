package com.sg.bjftviewprotect.system.common;

import com.sg.bjftviewprotect.system.constant.CommonConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangshuo
 * @Description
 * @Date 2024/5/9 14:07
 **/
public class TokenManager {
    private static final Map<String, String> userTokenMap = new HashMap<>();
    private static final Map<String, Long> tokenExpiryMap = new HashMap<>();
    private static final long SESSION_TIMEOUT_MS = 30 * 60 * 1000; // 30分钟
    private static final String TOKEN_ATTRIBUTE_PREFIX = "token_";

    public static void setUserToken(HttpServletRequest request, String account, String token) {
        HttpSession session = request.getSession();
        session.setAttribute(TOKEN_ATTRIBUTE_PREFIX + account, token);
        userTokenMap.put(account, token);
        long expiryTime = System.currentTimeMillis() + SESSION_TIMEOUT_MS;
        tokenExpiryMap.put(token, expiryTime);
    }

    public static String getToken(HttpServletRequest request, String account) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute(TOKEN_ATTRIBUTE_PREFIX + account);
        }
        return null;
    }

    // 验证过期时间
    public static boolean isTokenValid(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String account = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CommonConstant.X_USER_ACCOUNT)) {
                    account = cookie.getValue();
                    break;
                }
            }
        }
        if (account == null) {
            return false;
        }
        String token = getToken(request, account);
        Long expiryTime = tokenExpiryMap.get(token);
        return expiryTime != null && expiryTime >= System.currentTimeMillis();
    }

    // 调用此方法以刷新Token过期时间
    public static void onSuccessfulRequest(HttpServletRequest request,String account) {
        refreshToken(request, account);
    }

    public static void refreshToken(HttpServletRequest request, String account) {
        String token = getToken(request,account);
        if (token != null && tokenExpiryMap.containsKey(token)) {
            long newExpiryTime = System.currentTimeMillis() + SESSION_TIMEOUT_MS;
            tokenExpiryMap.put(token, newExpiryTime);
        }
    }

}
