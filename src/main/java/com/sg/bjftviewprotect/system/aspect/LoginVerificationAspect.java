package com.sg.bjftviewprotect.system.aspect;

import com.sg.bjftviewprotect.system.common.TokenManager;
import com.sg.bjftviewprotect.system.exception.NotLoggedInException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
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
@Component
@Slf4j
public class LoginVerificationAspect {
    @Before("@within(com.sg.bjftviewprotect.system.annotation.LoginVerification)")
    public void verification(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        if (!TokenManager.isTokenValid(request)){
            log.error("登陆已过期，请重新登陆");
            throw new NotLoggedInException("登陆已过期，请重新登陆");
        }

    }

    @After("@within(com.sg.bjftviewprotect.system.annotation.LoginVerification)")
    public void refreshToken(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        TokenManager.onSuccessfulRequest(request);
    }
}
