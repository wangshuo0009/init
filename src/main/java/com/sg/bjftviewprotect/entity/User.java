package com.sg.bjftviewprotect.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sg.bjftviewprotect.annotation.Desensitization;
import com.sg.bjftviewprotect.enums.DesensitizationTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("t_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @ApiModelProperty("名字")
    @TableField("name")
    private String name;

    @ApiModelProperty("帐号")
    @TableField("account")
    private String account;

    @Desensitization(type = DesensitizationTypeEnum.PASSWORD)
    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("状态1禁用0启用")
    @TableField("status")
    @TableLogic
    private Integer status;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("角色")
    @TableField(exist = false)
    private String roleName;


}