package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * @since 2024/05/22 09:49:18
 */
@Getter
@Setter
@TableName("t_power_user_info")
@Schema(name = "PowerUserInfo", description = "$!{table.comment}")
public class PowerUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId("id")
    private String id;

    @Schema(description = "用户编号")
    @TableField("user_code")
    private String userCode;

    @Schema(description = "code")
    @TableField("code")
    private String code;

    @Schema(description = "用户名称")
    @TableField("username")
    private String username;

    @Schema(description = "缴费户名")
    @TableField("payment_name")
    private String paymentName;

    @Schema(description = "现场用电情况")
    @TableField("address")
    private String address;

    @Schema(description = "重要情况")
    @TableField("important")
    private String important;

    @Schema(description = "市场化")
    @TableField("market")
    private String market;

    @Schema(description = "负荷类型")
    @TableField("load_type")
    private String loadType;

    @Schema(description = "1高压用户（商业用户），2低压用户（居民用户）")
    @TableField("type")
    private Integer type;

    @Schema(description = "出厂表号")
    @TableField("ammeter")
    private String ammeter;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

}
