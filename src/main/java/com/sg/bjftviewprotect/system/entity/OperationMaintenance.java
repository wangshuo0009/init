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
 * @since 2024/05/27 15:09:37
 */
@Getter
@Setter
@TableName("t_operation_maintenance")
@Schema(name = "OperationMaintenance", description = "$!{table.comment}")
public class OperationMaintenance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId("id")
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "编号1架空，2站室，3电缆,4整线,6用户分界")
    @TableField("code")
    private Integer code;

    @Schema(description = "类型1巡视工单，2故障工单")
    @TableField("type")
    private Integer type;

    @Schema(description = "数量")
    @TableField("num")
    private Integer num;

    @JsonFormat(pattern = "yyyy-MM")
    @Schema(description = "数据日期")
    @TableField("statistic_time")
    private LocalDateTime statisticTime;
}
