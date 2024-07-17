package com.sg.init.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName RouterLogAccess
 * @Author wangshuo
 * @Date 2024/4/29 12:18
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LoginVerification {
}
