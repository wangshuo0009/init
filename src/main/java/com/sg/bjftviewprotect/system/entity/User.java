package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sg.bjftviewprotect.system.annotation.Desensitization;
import com.sg.bjftviewprotect.system.enums.DesensitizationTypeEnum;
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
@TableName("t_user")
@Schema(name = "User", description = "$!{table.comment}")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @Schema(description = "名字")
    @TableField("name")
    private String name;

    @Schema(description = "帐号")
    @TableField("account")
    private String account;

    @Desensitization(type = DesensitizationTypeEnum.PASSWORD)
    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "是否启用1启用，0禁用")
    @TableField("is_enable")
    private Integer isEnable;

    @Schema(description = "是否删除1删除，0未删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "角色")
    @TableField(exist = false)
    private List<Role> roles;

    @Schema(description = "父id")
    @TableField("parent_id")
    private String parentId;



}
