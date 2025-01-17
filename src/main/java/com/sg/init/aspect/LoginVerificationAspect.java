package com.sg.init.aspect;

import com.sg.init.common.TokenManager;
import com.sg.init.exception.NotLoggedInException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginVerificationAspect
 * @Author wangshuo
 * @Date 2024/5/9 12:20
 * @Version 1.0
 **/
@Aspect
//@Component
@Slf4j
public class LoginVerificationAspect {
    @Before("@within(com.sg.init.annotation.LoginVerification)")
    public void verification(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 目前就验证时间
        if (!TokenManager.isTokenValid(request)){
            log.error("登陆已过期，请重新登陆");
            throw new NotLoggedInException("登陆已过期，请重新登陆");
        }

    }

    @After("@within(com.sg.init.annotation.LoginVerification)")
    public void refreshToken(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        TokenManager.onSuccessfulRequest(request);
    }
}
