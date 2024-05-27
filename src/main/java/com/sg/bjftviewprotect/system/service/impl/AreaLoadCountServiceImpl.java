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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, List<AreaLoadCount>> searchAreaLoadCountForView() {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<AreaLoadCount> list = areaLoadCountMapper.selectAreaLoadCountForView();
        List<AreaLoadCount> thisYear = new ArrayList<>();
        List<AreaLoadCount> lastYear = new ArrayList<>();
        for (AreaLoadCount areaLoadCount : list) {
            if (areaLoadCount.getStatisticTime().getYear() == localDateTime.getYear()) {
                thisYear.add(areaLoadCount);
            } else {
                lastYear.add(areaLoadCount);
            }
        }
        Map<String, List<AreaLoadCount>> map = new HashMap<>();
        map.put("thisYear", thisYear);
        map.put("lastYear", lastYear);

        return map;
    }
}
