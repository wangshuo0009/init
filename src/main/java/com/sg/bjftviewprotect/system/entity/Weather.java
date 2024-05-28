package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2024/05/28 09:35:04
 */
@Getter
@Setter
@TableName("t_weather")
@Schema(name = "Weather", description = "$!{table.comment}")
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId("id")
    private String id;

    @Schema(description = "天气1晴天，2雨天，3阴天，4雪")
    @TableField("weather")
    private Integer weather;

    @Schema(description = "温度，摄氏度")
    @TableField("temp")
    private Integer temp;

    @Schema(description = "湿度，%")
    @TableField("humidity")
    private Integer humidity;

    @Schema(description = "风向")
    @TableField("wind_direction")
    private String windDirection;

    @Schema(description = "风力")
    @TableField("wind_force")
    private Double windForce;

    @Schema(description = "降雨量mm")
    @TableField("rainfall")
    private Double rainfall;

    @Schema(description = "气压百帕（hpa）")
    @TableField("air_pressure")
    private Integer airPressure;

    @Schema(description = "统计时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("statistic_time")
    private LocalDateTime statisticTime;
}
