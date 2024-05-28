package com.sg.bjftviewprotect.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AreaElectricityCountRequest {

    @Schema(description = "id")
    private String id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "统计时间 yyyy-MM-01")
    private String statisticTime;

    @Schema(description = "区域用电量")
    private Double electricityUsage;

    @Schema(description = "创建时间")
    private String createTime;

    private Integer pageSize;
    private Integer pageNum;
}
