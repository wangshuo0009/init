package com.sg.bjftviewprotect.system.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.bjftviewprotect.system.entity.Role;
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
    public static Role adminRole;
    @PostConstruct
    public void init() {
        log.error("===========================================================================");
        log.error("==================《《《《开始初始化获取超级管理员角色》》》》====================");
        log.error("===========================================================================");
        // 在这里执行初始化逻辑
        // 超级管理员角色只能唯一 数据库里编码必须为 admin 不要修改
        adminRole = roleService.getOne(new LambdaQueryWrapper<Role>()
                .eq(Role::getCode, "admin")
        );

        log.error("===========================================================================");
        log.error("======================《《《《超级管理员角色获取成功》》》》》===================");
        log.error("===========================================================================");
    }
}
