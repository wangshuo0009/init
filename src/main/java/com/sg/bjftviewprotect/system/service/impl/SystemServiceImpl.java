package com.sg.bjftviewprotect.system.service.impl;

import com.sg.bjftviewprotect.system.mapper.SystemMapper;
import com.sg.bjftviewprotect.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName SystemServiceImpl
 * @Author wangshuo
 * @Date 2024/5/10 16:53
 * @Version 1.0
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemMapper systemMapper;


}
