package com.sg.bjftviewprotect.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
@Data
@TableName("t_menu")
@Schema(name = "Menu", description = "$!{table.comment}")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "编码")
    @TableField("code")
    private String code;


    @Schema(description = "是否启用1启用，0禁用")
    @TableField("is_enable")
    private Integer isEnable;

    @Schema(description = "是否删除1删除，0未删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @Schema(description = "类型 1三维，2后台管理系统")
    @TableField("type")
    private String type;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "菜单路由")
    @TableField("url")
    private String url;

    @Schema(description = "排序")
    @TableField("sort_no")
    private Integer sortNo;

    @Schema(description = "子模块")
    @TableField(exist = false)
    private List<MenuModules> menuModules;
}
