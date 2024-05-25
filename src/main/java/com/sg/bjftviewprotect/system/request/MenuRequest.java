package com.sg.bjftviewprotect.system.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "名称")
    private String name;

    @Schema(description = "编码")
    private String code;


    @Schema(description = "是否启用1启用，0禁用")
    private Integer isEnable;

    @Schema(description = "类型 1三维，2后台管理系统")
    private Integer type;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "菜单路由")
    private String url;


    @Schema(description = "三维路由")
    @TableField("three_view_url")
    private String threeViewUrl;

    @Schema(description = "排序")
    @TableField("sort_no")
    private Integer sortNo;

    private Integer pageSize;
    private Integer pageNum;

}
