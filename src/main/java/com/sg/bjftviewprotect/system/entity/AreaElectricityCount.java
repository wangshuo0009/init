package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/27 10:18:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_area_electricity_count")
@Schema(name = "AreaElectricityCount", description = "$!{table.comment}")
public class AreaElectricityCount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId("id")
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "统计时间")
    @JsonFormat(pattern = "yyyy-MM")
    @TableField("statistic_time")
    private LocalDateTime statisticTime;

    @Schema(description = "区域用电量")
    @TableField("electricity_usage")
    private Double electricityUsage;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}
