package com.sg.bjftviewprotect.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.OperationMaintenance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.bjftviewprotect.system.request.OperationMaintenanceRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/27 15:09:37
 */
@Mapper
public interface OperationMaintenanceMapper extends BaseMapper<OperationMaintenance> {

    Page<OperationMaintenance> selectOperationMaintenance(@Param("page") Page<OperationMaintenance> page, @Param("operationMaintenanceRequest") OperationMaintenanceRequest operationMaintenanceRequest);

    List<OperationMaintenance> selectOperationMaintenanceForView(@Param("type") int type);
}
