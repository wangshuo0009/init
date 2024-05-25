package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.entity.AreaLoadCount;
import com.sg.bjftviewprotect.system.mapper.AreaLoadCountMapper;
import com.sg.bjftviewprotect.system.request.AreaLoadCountRequest;
import com.sg.bjftviewprotect.system.service.AreaLoadCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/24 10:47:39
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AreaLoadCountServiceImpl extends ServiceImpl<AreaLoadCountMapper, AreaLoadCount> implements AreaLoadCountService {
    @Autowired
    private AreaLoadCountMapper areaLoadCountMapper;
    @Override
    public Page<AreaLoadCount> searchAreaLoadCount(AreaLoadCountRequest areaLoadCountRequest) {
        Page<AreaLoadCount> page = new Page<>(areaLoadCountRequest.getPageNum(), areaLoadCountRequest.getPageSize());
        return areaLoadCountMapper.selectAreaLoadCount(page, areaLoadCountRequest);
    }

    @Override
    public Page<AreaLoadCount> searchAreaLoadCount() {
        //return areaLoadCountMapper.selectAreaLoadCount();
        return null;
    }
}
