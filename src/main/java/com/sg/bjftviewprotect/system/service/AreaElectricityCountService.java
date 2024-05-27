package com.sg.bjftviewprotect.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.AreaElectricityCount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.request.AreaElectricityCountRequest;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/27 10:18:30
 */
public interface AreaElectricityCountService extends IService<AreaElectricityCount> {

    // 区域用电量档案
    Page<AreaElectricityCount> searchAreaElectricityCount(AreaElectricityCountRequest areaElectricityCountRequest);

    // 区域用电量三维统计
    Map<String, List<AreaElectricityCount>> searchAreaElectricityCountForView();
}
