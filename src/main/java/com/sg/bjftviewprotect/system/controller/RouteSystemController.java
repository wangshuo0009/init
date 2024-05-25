package com.sg.bjftviewprotect.system.controller;

import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SystemController
 * @Author wangshuo
 * @Date 2024/5/9 13:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/route")
@Tag(name = "路由信息")
public class RouteSystemController {
    @Autowired
    private MenuService menuService;
    @Operation(summary = "后台管理菜单路由", description = "1是数据管理，2是系统管理路由\t包含某个页面下的小模块")
    @GetMapping("/managerSystem")
    public Result<?> managerSystem() {
        Object o = menuService.searchAllByType(CommonConstant.MANAGER_SYSTEM_TYPE_CODE);
        return Result.success("查询成功", o);
    }



}
