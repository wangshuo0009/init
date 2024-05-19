package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.entity.Config;
import com.sg.bjftviewprotect.system.mapper.ConfigMapper;
import com.sg.bjftviewprotect.system.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/17 11:20:58
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;
    @Override
    public Config getSuperAdmin() {
        return configMapper.selectOne(new LambdaQueryWrapper<Config>().eq(Config::getName, "超级管理员"));
    }


}
