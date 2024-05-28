package com.sg.bjftviewprotect.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.annotation.LoginVerification;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.Menu;
import com.sg.bjftviewprotect.system.request.MenuRequest;
import com.sg.bjftviewprotect.system.service.MenuModulesService;
import com.sg.bjftviewprotect.system.service.MenuService;
import com.sg.bjftviewprotect.system.util.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
@RestController
@RequestMapping("/menu")
@Tag(name = "菜单管理")
@LoginVerification
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuModulesService menuModulesService;

    @Operation(summary = "角色管理-绑定菜单列表", tags = "角色管理")
    @GetMapping("searchAllMenu")
    //public Result<?> searchAllMenu(@CookieValue(value = CommonConstant.X_USER_ID) String userId) {
    public Result<Page<Menu>> searchAllMenu(@RequestHeader(value = CommonConstant.X_USER_ID) String userId) {
        MenuRequest menuRequest = PageUtil.pageForList(new MenuRequest());
        Page<Menu> page = menuService.searchMenu(menuRequest, userId);
        return Result.success("查询成功", page);
    }

    @Operation(summary = "查询菜单信息")
    @PostMapping("searchMenu")
    public Result<Page<Menu>> searchMenu(@RequestBody MenuRequest menuRequest,
                                //@CookieValue(value = CommonConstant.X_USER_ID) String userId) {
                                @RequestHeader(value = CommonConstant.X_USER_ID) String userId) {
        PageUtil.initPage(menuRequest);
        Page<Menu> page = menuService.searchMenu(menuRequest, userId);
        return Result.success("查询成功", page);
    }

    @Operation(summary = "新增菜单")
    @PostMapping("saveMenu")
    public Result<?> saveMenu(@RequestBody MenuRequest menuRequest) {
        try {
            parameterValidation(menuRequest);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
        Menu menu = new Menu() {{
            setName(menuRequest.getName());
            setUrl(menuRequest.getUrl());
            setCode(menuRequest.getCode());
            setIsEnable(CommonConstant.IS_ENABLE);
            setIsDelete(CommonConstant.NOT_DELETE);
            setType(menuRequest.getType());
            setCreateTime(LocalDateTime.now());
            setSortNo(menuRequest.getSortNo());
        }};
        menuService.save(menu);
        return Result.success("新增成功");
    }

    @Operation(summary = "更新菜单")
    @PostMapping("updateMenu")
    public Result<?> updateMenu(@RequestBody MenuRequest menuRequest) {
        try {
            parameterValidation(menuRequest);
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
        Menu menu = new Menu() {{
            setId(menuRequest.getId());
            setName(menuRequest.getName());
            setUrl(menuRequest.getUrl());
            setCode(menuRequest.getCode());
            setIsEnable(CommonConstant.IS_ENABLE);
            setIsDelete(CommonConstant.NOT_DELETE);
            setType(menuRequest.getType());
            setSortNo(menuRequest.getSortNo());
        }};
        menuService.updateById(menu);
        return Result.success("更新成功");
    }


    /**
     * 删除菜单
     */
    @Operation(summary = "删除菜单")
    @DeleteMapping("/deleteMenu/{id}")
    public Result<?> deleteMenu(@PathVariable("id") String id) {
        menuService.removeById(id);
        return Result.success("删除成功");
    }


    /**
     * 参数验证
     */
    public void parameterValidation(MenuRequest request) {
        if (StringUtils.isBlank(request.getCode())) {
            throw new RuntimeException("编码不能为空");
        }
        if (request.getType() == null) {
            throw new RuntimeException("类型不能为空");
        }
        Menu one = menuService.getOne(new LambdaQueryWrapper<Menu>()
                .ne(StringUtils.isNotBlank(request.getId()), Menu::getId, request.getId())
                .eq(Menu::getCode, request.getCode()), false);
        if (!ObjectUtils.isEmpty(one) && StringUtils.equals(one.getCode(),request.getCode())) {
            throw new RuntimeException("编码重复");
        }
    }
}
