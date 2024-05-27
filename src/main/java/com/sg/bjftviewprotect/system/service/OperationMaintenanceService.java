package com.sg.bjftviewprotect.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.OperationMaintenance;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.entity.OperationMaintenanceUtil;
import com.sg.bjftviewprotect.system.request.OperationMaintenanceRequest;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/27 15:09:37
 */
public interface OperationMaintenanceService extends IService<OperationMaintenance> {

    Page<OperationMaintenance> searchOperationMaintenance(OperationMaintenanceRequest operationMaintenanceRequest);

    Map<Integer, OperationMaintenanceUtil> searchOperationMaintenanceForView(Integer type);
}
