package com.sg.bjftviewprotect.request;

import lombok.Data;

/**
 * @ClassName UserLoginRequest
 * @Author wangshuo
 * @Date 2024/5/9 13:37
 * @Version 1.0
 **/
@Data
public class UserLoginRequest {
    public String account;
    public String password;
}
