package com.sg.bjftviewprotect.system.service;

import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.RegionalIntroduction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.request.RegionalIntroductionRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 09:30:21
 */
public interface RegionalIntroductionService extends IService<RegionalIntroduction> {
    Result<?> saveOrUpdateRegionalIntroduction(RegionalIntroductionRequest regionalIntroductionRequest);

}
