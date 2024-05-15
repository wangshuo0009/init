package com.sg.bjftviewprotect.common;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;

@ApiModel(value = "统一返回状态码常量")
public interface ResultCodeConst {

    @Schema(description = "成功")
    Integer SUCCESS = 200;
    @Schema(description = "失败")
    Integer FAIL = 500;
}