package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/20 09:03:26
 */
@Getter
@Setter
@TableName("t_area_user_count")
@Schema(name = "AreaUserCount", description = "$!{table.comment}")
public class AreaUserCount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableField("id")
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "数量")
    @TableField("num")
    private Integer num;

    @Schema(description = "占比")
    @TableField("rate")
    private Double rate;
}
