package com.sg.bjftviewprotect.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName RoleRequest
 * @Author wangshuo
 * @Date 2024/5/13 15:29
 * @Version 1.0
 **/
@Data
public class RoleRequest {
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("编码")
    private String code;


    @ApiModelProperty("是否启用1启用，0禁用")
    private Integer isEnable;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("菜单")
    private List<String> menuId;


    private Integer pageSize;
    private Integer pageNum;
}
