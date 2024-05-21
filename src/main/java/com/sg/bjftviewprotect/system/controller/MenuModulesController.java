package com.sg.bjftviewprotect.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sg.bjftviewprotect.system.annotation.LoginVerification;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.MenuModules;
import com.sg.bjftviewprotect.system.service.MenuModulesService;
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
@LoginVerification
public class MenuModulesController {

    @Autowired
    private MenuModulesService menuModulesService;


    @Operation(summary = "新增更和新子模块")
    @PostMapping("/saveOrUpdateMenuModules")
    public Result<?> saveOrUpdateMenuModules(@RequestBody MenuModules menuModules) {
        // 参数验证
        try {
            parameterValidation(menuModules);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        menuModulesService.saveOrUpdate(menuModules);
        return Result.success("操作成功");
    }

    @Operation(summary = "删除子模块")
    @DeleteMapping("/deleteMenuModules/{id}")
    public Result<?> deleteMenuModules(@PathVariable("id") String id) {
        menuModulesService.removeById(id);
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
