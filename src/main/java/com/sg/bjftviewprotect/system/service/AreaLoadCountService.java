package com.sg.bjftviewprotect.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.AreaLoadCount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.request.AreaLoadCountRequest;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/24 10:47:39
 */
public interface AreaLoadCountService extends IService<AreaLoadCount> {

    // 查询负荷所有档案
    Page<AreaLoadCount> searchAreaLoadCount(AreaLoadCountRequest areaLoadCountRequest);

    // 查询负荷查询三维的
    Map<String, List<AreaLoadCount>> searchAreaLoadCountForView();

}
