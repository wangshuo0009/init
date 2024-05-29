package com.sg.bjftviewprotect.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/28 10:51:09
 */
@Getter
@Setter
public class FaultAlarmRequest{


    @Schema(description = "id")
    private String id;

    @Schema(description = "开关站")
    private String station;

    @Schema(description = "告警台账")
    private String ledger;

    @Schema(description = "点位名称")
    private String pointName;

    @Schema(description = "巡视值")
    private String patrol;

    @Schema(description = "巡视时间 yyyy-MM-dd HH:mm:ss")
    private String statisticTime;

    @Schema(description = "告警内容")
    private String alarmContent;


    @Schema(description = "告警图片，先不做")
    private MultipartFile alarmImage;

    private Integer pageSize;
    private Integer pageNum;
}
