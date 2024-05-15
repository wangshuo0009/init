package com.sg.bjftviewprotect.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.MenuModules;
import com.sg.bjftviewprotect.system.service.MenuModulesService;
import com.sg.bjftviewprotect.system.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/15 16:46:05
 */
@RestController
@RequestMapping("/menuModules")
@Tag(name = "菜单子模块管理")
public class MenuModulesController {

    @Autowired
    private MenuModulesService menuModulesService;

    @Autowired
    private MenuService menuService;

    @Operation(summary = "三维菜单路由信息查询", description = "包含某个页面下的小模块")
    @GetMapping("/threeDimensional")
    public Result<?> threeDimensional() {
        return getMenuAndMenuModules(CommonConstant.THREE_DIMENSIONAL_TYPE_CODE);
    }

    @Operation(summary = "后台管理系统菜单路由信息", description = "包含某个页面下的小模块")
    @GetMapping("/managerSystem")
    public Result<?> managerSystem() {
        return getMenuAndMenuModules(CommonConstant.MANAGER_SYSTEM_TYPE_CODE);
    }

    public Result<?> getMenuAndMenuModules(Integer type) {
        return menuService.searchAllByType(type);
    }

    @Operation(summary = "新增更新子模块")
    @GetMapping("/saveOrUpdateMenuModules")
    public Result<?> saveOrUpdateMenuModules(@RequestBody MenuModules menuModules) {
        // 参数验证
        try {
            parameterValidation(menuModules);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        menuModulesService.saveOrUpdate(menuModules);
        return Result.success("新增成功");
    }

    @Operation(summary = "删除子模块")
    @DeleteMapping("/deleteMenuModules")
    public Result<?> deleteMenuModules(@RequestParam("menuModulesId") String menuModulesId) {
        menuModulesService.removeById(menuModulesId);
        return Result.success("新增成功");
    }


    /**
     * 参数验证
     */
    public void parameterValidation(MenuModules menuModules){
        if (StringUtils.isBlank(menuModules.getCode())) {
            throw new RuntimeException("编号不能为空");
        }
        if (StringUtils.isBlank(menuModules.getMenuId())) {
            throw new RuntimeException("父菜单不能为空");
        }
        MenuModules one = menuModulesService.getOne(new LambdaQueryWrapper<MenuModules>().eq(MenuModules::getCode, menuModules.getCode()),false);
        if (!ObjectUtils.isEmpty(one)) {
            throw new RuntimeException("帐号重复");
        }
    }


}
