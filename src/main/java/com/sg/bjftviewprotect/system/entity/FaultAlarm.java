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
 * @since 2024/05/28 10:51:09
 */
@Getter
@Setter
@TableName("t_fault_alarm")
@Schema(name = "FaultAlarm", description = "$!{table.comment}")
public class FaultAlarm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId("id")
    private String id;

    @Schema(description = "开关站")
    @TableField("station")
    private String station;

    @Schema(description = "告警台账")
    @TableField("ledger")
    private String ledger;

    @Schema(description = "点位名称")
    @TableField("point_name")
    private String pointName;

    @Schema(description = "巡视值")
    @TableField("patrol")
    private String patrol;

    @Schema(description = "巡视时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("statistic_time")
    private LocalDateTime statisticTime;

    @Schema(description = "告警内容")
    @TableField("alarm_content")
    private String alarmContent;

    @Schema(description = "告警图片")
    @TableField("alarm_image")
    private String alarmImage;
}
