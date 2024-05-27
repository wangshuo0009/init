package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.entity.AreaElectricityCount;
import com.sg.bjftviewprotect.system.mapper.AreaElectricityCountMapper;
import com.sg.bjftviewprotect.system.request.AreaElectricityCountRequest;
import com.sg.bjftviewprotect.system.service.AreaElectricityCountService;
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
 * @since 2024/05/27 10:18:30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaElectricityCountServiceImpl extends ServiceImpl<AreaElectricityCountMapper, AreaElectricityCount> implements AreaElectricityCountService {
    @Autowired
    private AreaElectricityCountMapper areaElectricityCountMapper;

    @Override
    public Page<AreaElectricityCount> searchAreaElectricityCount(AreaElectricityCountRequest areaElectricityCountRequest) {
        Page<AreaElectricityCount> page = new Page<>(areaElectricityCountRequest.getPageNum(), areaElectricityCountRequest.getPageSize());
        areaElectricityCountMapper.selectAreaElectricityCount(page,areaElectricityCountRequest);
        return page;
    }


    @Override
    public Map<String, List<AreaElectricityCount>> searchAreaElectricityCountForView() {

        LocalDateTime localDateTime = LocalDateTime.now();
        List<AreaElectricityCount> list = areaElectricityCountMapper.selectAreaElectricityCountForView();
        List<AreaElectricityCount> thisYear = new ArrayList<>();
        List<AreaElectricityCount> lastYear = new ArrayList<>();
        for (AreaElectricityCount areaElectricityCount : list) {
            if (areaElectricityCount.getStatisticTime().getYear() == localDateTime.getYear()) {
                thisYear.add(areaElectricityCount);
            } else {
                lastYear.add(areaElectricityCount);
            }
        }
        Map<String, List<AreaElectricityCount>> map = new HashMap<>();
        map.put("thisYear", thisYear);
        map.put("lastYear", lastYear);
        return map;
    }
}
