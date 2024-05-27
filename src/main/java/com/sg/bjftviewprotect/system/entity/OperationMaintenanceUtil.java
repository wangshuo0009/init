package com.sg.bjftviewprotect.system.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName OperationMaintenanceUtil
 * @Author wangshuo
 * @Date 2024/5/27 17:28
 * @Version 1.0
 **/
@Data
public class OperationMaintenanceUtil {

    @Schema(description = "数据")
    private Integer count;
    @Schema(description = "OperationMaintenance")
    private List<OperationMaintenance> operationMaintenanceList;
}
