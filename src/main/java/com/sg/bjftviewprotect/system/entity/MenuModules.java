package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/15 16:46:05
 */
@Getter
@Setter
@TableName("t_menu_modules")
@Schema(name = "MenuModules", description = "$!{table.comment}")
public class MenuModules implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "编码")
    @TableField("code")
    private String code;

    @Schema(description = "状态1启用 0禁用")
    @TableField("is_enable")
    private Integer isEnable;

    @Schema(description = "菜单id")
    @TableField("menu_id")
    private String menuId;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "菜单路由")
    @TableField("url")
    private String url;

    @Schema(description = "1删除，0未删除")
    @TableField("is_delete")
    private Integer isDelete;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;
}
