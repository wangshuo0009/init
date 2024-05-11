package com.sg.bjftviewprotect.service.impl;

import com.sg.bjftviewprotect.mapper.SystemMapper;
import com.sg.bjftviewprotect.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SystemServiceImpl
 * @Author wangshuo
 * @Date 2024/5/10 16:53
 * @Version 1.0
 **/
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemMapper systemMapper;


}
