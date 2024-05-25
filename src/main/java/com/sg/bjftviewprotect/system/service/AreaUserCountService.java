package com.sg.bjftviewprotect.system.service;

import com.sg.bjftviewprotect.system.entity.AreaUserCount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/20 09:03:26
 */
public interface AreaUserCountService extends IService<AreaUserCount> {

    List<AreaUserCount> searchAreaUserCount();
}
