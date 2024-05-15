package com.sg.bjftviewprotect.system.service.impl;

import com.sg.bjftviewprotect.system.entity.RegionalIntroduction;
import com.sg.bjftviewprotect.system.mapper.RegionalIntroductionMapper;
import com.sg.bjftviewprotect.system.service.RegionalIntroductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 09:30:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RegionalIntroductionServiceImpl extends ServiceImpl<RegionalIntroductionMapper, RegionalIntroduction> implements RegionalIntroductionService {

}
