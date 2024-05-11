package com.sg.bjftviewprotect.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@TableName("t_menu")
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("编码")
    @TableField("code")
    private String code;

    @ApiModelProperty("状态1禁用0启用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("菜单路由")
    @TableField("url")
    private String url;
}