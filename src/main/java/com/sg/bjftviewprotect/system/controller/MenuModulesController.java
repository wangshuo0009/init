package com.sg.bjftviewprotect.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.annotation.LoginVerification;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.MenuModules;
import com.sg.bjftviewprotect.system.service.MenuModulesService;
import com.sg.bjftviewprotect.system.util.PageUtil;
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

    @Operation(summary = "查询菜单指标模块",tags = "菜单管理")
    @GetMapping("/searchMenuModules")
    public Result<?> searchMenuModules(@RequestParam(value = "id") String id) {
        Page<MenuModules> page = PageUtil.createPageForList();
        menuModulesService.page(page, new LambdaQueryWrapper<MenuModules>()
                .eq(StringUtils.isNotBlank(id), MenuModules::getMenuId, id)
                .orderBy(true, true, MenuModules::getSortNo));
        return Result.success("查询成功",page);
    }


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
    public void parameterValidation(MenuModules request){
        if (StringUtils.isBlank(request.getCode())) {
            throw new RuntimeException("编号不能为空");
        }
        if (StringUtils.isBlank(request.getMenuId())) {
            throw new RuntimeException("父菜单不能为空");
        }
        MenuModules one = menuModulesService.getOne(new LambdaQueryWrapper<MenuModules>()
                .ne(StringUtils.isNotBlank(request.getId()), MenuModules::getId, request.getId())
                .eq(MenuModules::getCode, request.getCode()),false);
        if (!ObjectUtils.isEmpty(one) && StringUtils.equals(one.getCode(),request.getCode())) {
            throw new RuntimeException("帐号重复");
        }
    }


}
