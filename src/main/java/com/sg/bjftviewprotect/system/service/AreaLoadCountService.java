package com.sg.bjftviewprotect.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.AreaLoadCount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.request.AreaLoadCountRequest;

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

    Page<AreaLoadCount> searchAreaLoadCount();

}
