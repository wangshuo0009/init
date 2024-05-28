package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.entity.Weather;
import com.sg.bjftviewprotect.system.mapper.WeatherMapper;
import com.sg.bjftviewprotect.system.service.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/28 09:35:04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WeatherServiceImpl extends ServiceImpl<WeatherMapper, Weather> implements WeatherService {

}
