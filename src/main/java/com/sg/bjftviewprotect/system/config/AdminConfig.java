package com.sg.bjftviewprotect.system.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.sg.bjftviewprotect.system.entity.Config;
import com.sg.bjftviewprotect.system.entity.Role;
import com.sg.bjftviewprotect.system.service.ConfigService;
import com.sg.bjftviewprotect.system.service.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ClassName AdminConfig
 * @Author wangshuo
 * @Date 2024/5/15 13:04
 * @Version 1.0
 **/
@Configuration
@Log4j2
public class AdminConfig {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ConfigService configService;
    public static Role adminRole;
    @PostConstruct
    public void init() {
        log.error("===========================================================================");
        log.error("==================《《《《开始初始化获取超级管理员角色》》》》====================");
        log.error("===========================================================================");
        Config superAdmin = configService.getSuperAdmin();

        // 在这里执行初始化逻辑
        adminRole = roleService.getOne(new LambdaQueryWrapper<Role>()
                .eq(Role::getId, superAdmin.getCode())
        );

        log.error("===========================================================================");
        if (ObjectUtils.isEmpty(adminRole)) {
            log.error("======================《《《《超级管理员角色获取失败》》》》》===================");
        }
        log.error("======================《《《《超级管理员角色获取成功》》》》》===================");
        log.error("===========================================================================");
    }
}
