package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.entity.OperationMaintenance;
import com.sg.bjftviewprotect.system.entity.OperationMaintenanceUtil;
import com.sg.bjftviewprotect.system.mapper.OperationMaintenanceMapper;
import com.sg.bjftviewprotect.system.request.OperationMaintenanceRequest;
import com.sg.bjftviewprotect.system.service.OperationMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/27 15:09:37
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationMaintenanceServiceImpl extends ServiceImpl<OperationMaintenanceMapper, OperationMaintenance> implements OperationMaintenanceService {
    @Autowired
    private OperationMaintenanceMapper operationMaintenanceMapper;
    @Override
    public Page<OperationMaintenance> searchOperationMaintenance(OperationMaintenanceRequest operationMaintenanceRequest) {
        Page<OperationMaintenance> page = new Page<>(operationMaintenanceRequest.getPageNum(), operationMaintenanceRequest.getPageSize());
        operationMaintenanceMapper.selectOperationMaintenance(page, operationMaintenanceRequest);
        return page;
    }

    @Override
    public Map<Integer, OperationMaintenanceUtil> searchOperationMaintenanceForView(Integer type) {
        List<OperationMaintenance> operationMaintenances = operationMaintenanceMapper.selectOperationMaintenanceForView(type);
        Map<Integer, List<OperationMaintenance>> map = operationMaintenances.stream().collect(Collectors.groupingBy(OperationMaintenance::getCode));

        Map<Integer, OperationMaintenanceUtil> finalMap = new HashMap<>();
        for (Map.Entry<Integer, List<OperationMaintenance>> entry : map.entrySet()) {
            int number = entry.getValue().stream().mapToInt(OperationMaintenance::getNum).sum();
            OperationMaintenanceUtil operationMaintenanceUtil = new OperationMaintenanceUtil() {{
                setCount(number);
                setOperationMaintenanceList(entry.getValue());
            }};
            finalMap.put(entry.getKey(), operationMaintenanceUtil);
        }
        return finalMap;
    }

}
