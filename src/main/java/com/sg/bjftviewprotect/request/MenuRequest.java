package com.sg.bjftviewprotect.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MenuRequest
 * @Author wangshuo
 * @Date 2024/5/14 15:04
 * @Version 1.0
 **/
@Data
public class MenuRequest {
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("编码")
    private String code;


    @ApiModelProperty("是否启用1启用，0禁用")
    private Integer isEnable;

    @ApiModelProperty("类型 1三维，2后台管理系统")
    private String type;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("菜单路由")
    private String url;



    private Integer pageSize;
    private Integer pageNum;

}
