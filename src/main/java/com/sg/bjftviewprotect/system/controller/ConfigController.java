package com.sg.bjftviewprotect.system.controller;

import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.config.AdminConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/17 11:20:58
 */
@RestController
@RequestMapping("/config")
@Tag(name = "系统配置管理")
public class ConfigController {
    @Autowired
    private AdminConfig adminConfig;
    @Operation(summary = "刷新管理员角色信息", description = "预留接口省去重启服务，作用不大")
    @GetMapping("refreshRole")
    public Result<?> refreshRole(){
        adminConfig.init();
        return Result.success("刷新成功", AdminConfig.adminRole);
    }

}
