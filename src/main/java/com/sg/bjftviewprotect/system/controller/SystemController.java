package com.sg.bjftviewprotect.system.controller;

import com.sg.bjftviewprotect.system.annotation.LoginVerification;
import com.sg.bjftviewprotect.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SystemController
 * @Author wangshuo
 * @Date 2024/5/9 13:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/system")
@LoginVerification
public class SystemController {

    @Autowired
    private SystemService systemService;





}
