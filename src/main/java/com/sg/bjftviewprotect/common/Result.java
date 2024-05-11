package com.sg.bjftviewprotect.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@ApiModel(value = "统一返回")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回状态码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T data;
    @ApiModelProperty(value = "token验证")
    private String token;

    public Result(HttpStatus unauthorized, String message, Object o, Object object) {
    }

    public Result(Boolean success, Integer code, String message, T data, String token) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.token = token;
    }

    public static Result getInstance(Boolean success, Integer code, String message, Object data, String token) {
        return new Result(success, code, message, data, token);
    }

    public static Result success() {
        return getInstance(true, ResultCodeConst.SUCCESS, null, null, null);
    }

    public static Result success(String message) {
        return getInstance(true, ResultCodeConst.SUCCESS, message, null, null);
    }

    public static Result success(String message, Object data) {
        return getInstance(true, ResultCodeConst.SUCCESS, message, data, null);
    }

    public static Result success(Integer code, String message, Object data, String token) {
        return getInstance(true, code, message, data, token);
    }

    public static Result fail() {
        return getInstance(false, ResultCodeConst.FAIL, null, null, null);
    }

    public static Result fail(String message) {
        return getInstance(false, ResultCodeConst.FAIL, message, null, null);
    }

    public static Result fail(String message, Object data) {
        return getInstance(false, ResultCodeConst.FAIL, message, data, null);
    }

    public static Result fail(Integer code, String message, Object data, String token) {
        return getInstance(false, code, message, data, token);
    }
    public Result(Boolean success) {
        this(success, null);
    }

    public Result(Boolean success, String message) {
        this(success, null, message);
    }

    public Result(Boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
}