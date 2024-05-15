package com.sg.bjftviewprotect.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/11 11:45:34
 */
@Data
@TableName("t_role_menu")
@Schema(name = "RoleMenu", description = "$!{table.comment}")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @Schema(description = "角色id")
    @TableField("role_id")
    private String roleId;

    @Schema(description = "菜单id")
    @TableField("menu_id")
    private String menuId;
}
