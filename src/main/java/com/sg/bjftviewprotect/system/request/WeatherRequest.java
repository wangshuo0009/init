package com.sg.bjftviewprotect.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

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
public class WeatherRequest{
    @Schema(description = "id")
    private String id;

    @Schema(description = "天气1晴天，2雨天，3阴天，4雪")
    private Integer weather;

    @Schema(description = "温度，摄氏度")
    private Integer temp;

    @Schema(description = "湿度，%")
    private Integer humidity;

    @Schema(description = "风向")
    private String windDirection;

    @Schema(description = "风力")
    private Double windForce;

    @Schema(description = "降雨量mm")
    private Double rainfall;

    @Schema(description = "气压百帕（hpa）")
    private Integer airPressure;

    @Schema(description = "统计时间 yyyy-MM-dd")
    private String statisticTime;
}
