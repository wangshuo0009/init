package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.entity.AreaUserCount;
import com.sg.bjftviewprotect.system.mapper.AreaUserCountMapper;
import com.sg.bjftviewprotect.system.service.AreaUserCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/20 09:03:26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaUserCountServiceImpl extends ServiceImpl<AreaUserCountMapper, AreaUserCount> implements AreaUserCountService {

    @Autowired
    private AreaUserCountMapper areaUserCountMapper;

    @Override
    public List<AreaUserCount> searchAreaUserCount() {
        return areaUserCountMapper.selectList(null);
    }
}
