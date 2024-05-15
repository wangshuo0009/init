package com.sg.bjftviewprotect.request;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "名称")
    private String name;

    @Schema(description = "编码")
    private String code;


    @Schema(description = "是否启用1启用，0禁用")
    private Integer isEnable;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "菜单ID 数组")
    private List<String> menuId;


    private Integer pageSize;
    private Integer pageNum;
}
