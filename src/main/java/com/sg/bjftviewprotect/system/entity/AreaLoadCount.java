package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/24 10:47:39
 */
@Getter
@Setter
@TableName("t_area_load_count")
@Schema(name = "AreaLoadCount", description = "$!{table.comment}")
public class AreaLoadCount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId("id")
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "统计时间")
    @TableField("statistic_time")
    private LocalDateTime statisticTime;

    @Schema(description = "负荷，用电量")
    @TableField("power_load")
    private BigDecimal powerLoad;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

}
