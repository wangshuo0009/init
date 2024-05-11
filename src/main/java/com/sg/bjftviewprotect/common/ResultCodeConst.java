package com.sg.bjftviewprotect.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "统一返回状态码常量")
public interface ResultCodeConst {

    @ApiModelProperty(value = "成功")
    Integer SUCCESS = 200;
    @ApiModelProperty(value = "失败")
    Integer FAIL = 500;
}