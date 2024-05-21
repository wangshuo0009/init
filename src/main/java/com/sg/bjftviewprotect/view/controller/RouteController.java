package com.sg.bjftviewprotect.view.controller;

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
@Tag(name = "三维菜单路由")
public class RouteController {
    @Autowired
    private MenuService menuService;
    @Operation(summary = "三维菜单路由信息查询", description = "包含某个页面下的小模块")
    @GetMapping("/threeDimensional")
    public Result<?> threeDimensional() {
        return menuService.searchAllByType(CommonConstant.THREE_DIMENSIONAL_TYPE_CODE);
    }

}
