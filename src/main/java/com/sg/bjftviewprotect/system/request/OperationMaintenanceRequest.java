package com.sg.bjftviewprotect.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/27 15:09:37
 */
@Data
public class OperationMaintenanceRequest{

    @Schema(description = "id")
    private String id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "编号1架空，2站室，3电缆,4整线,6用户分界")
    private Integer code;
    @Schema(description = "类型1巡视工单，2故障工单")
    private Integer type;
    @Schema(description = "数量")
    private Integer num;
    @Schema(description = "数据日期 yyyy-MM-01")
    private String statisticTime;
    private Integer pageSize;
    private Integer pageNum;
}
