package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.entity.FaultAlarm;
import com.sg.bjftviewprotect.system.mapper.FaultAlarmMapper;
import com.sg.bjftviewprotect.system.service.FaultAlarmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/28 10:51:09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FaultAlarmServiceImpl extends ServiceImpl<FaultAlarmMapper, FaultAlarm> implements FaultAlarmService {

}
