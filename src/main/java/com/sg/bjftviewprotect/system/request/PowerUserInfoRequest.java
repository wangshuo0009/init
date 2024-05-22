package com.sg.bjftviewprotect.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName PowerUserInfoRequest
 * @Author wangshuo
 * @Date 2024/5/22 12:55
 * @Version 1.0
 **/
@Data
public class PowerUserInfoRequest {
    @Schema(description = "id")
    private String id;

    @Schema(description = "用户编号")
    private String userCode;

    @Schema(description = "code")
    private String code;

    @Schema(description = "用户名称")
    private String username;

    @Schema(description = "缴费户名")
    private String paymentName;

    @Schema(description = "现场用电情况")
    private String address;

    @Schema(description = "重要情况")
    private String important;

    @Schema(description = "市场化")
    private String market;

    @Schema(description = "负荷类型")
    private String loadType;

    @Schema(description = "1高压用户（商业用户），2低压用户（居民用户）")
    private Integer type;

    @Schema(description = "出厂表号")
    private String ammeter;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    private Integer pageSize;
    private Integer pageNum;
}
