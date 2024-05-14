package com.sg.bjftviewprotect.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
@Getter
@Setter
@TableName("t_role")
@ApiModel(value = "Role对象", description = "")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("编码")
    @TableField("code")
    private String code;


    @ApiModelProperty("是否启用1启用，0禁用")
    @TableField("is_enable")
    private Integer isEnable;

    @ApiModelProperty("是否删除1删除，0未删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("权限等级")
    @TableField("permission_level")
    private String permissionLevel;

    @ApiModelProperty("权限等级")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty("菜单")
    @TableField(exist = false)
    private List<Menu> menus;
}
