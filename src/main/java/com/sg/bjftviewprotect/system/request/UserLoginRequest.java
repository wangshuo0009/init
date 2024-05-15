package com.sg.bjftviewprotect.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName UserLoginRequest
 * @Author wangshuo
 * @Date 2024/5/9 13:37
 * @Version 1.0
 **/
@Data
public class UserLoginRequest {
    @Schema(description = "账户")
    public String account;
    @Schema(description = "密码")
    public String password;
}
