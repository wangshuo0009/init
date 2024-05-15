package com.sg.bjftviewprotect.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.annotation.LoginVerification;
import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.constant.CommonConstant;
import com.sg.bjftviewprotect.entity.Menu;
import com.sg.bjftviewprotect.request.MenuRequest;
import com.sg.bjftviewprotect.service.MenuService;
import com.sg.bjftviewprotect.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sg.bjftviewprotect.config.AdminConfig.adminRole;

/**
 * <p>
 *  前端控制器
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
    private UserRoleService userRoleService;

    @Operation(summary = "查询所有菜单信息")
    @GetMapping("searchAllMenu")
    public Result<?> searchAllMenu() {
        return Result.success("查询成功", menuService.list());
    }

    @Operation(summary = "查询菜单信息")
    @PostMapping("searchMenu")
    public Result<?> searchMenu(@RequestBody MenuRequest menuRequest,
                                @CookieValue(value = CommonConstant.X_USER_ID) String userId) {
        List<String> roleChildIds = userRoleService.searchRoleChildIds(userId);
        // 超级管理员角色处理逻辑
        if (!ObjectUtils.isEmpty(roleChildIds) && roleChildIds.contains(adminRole.getId())) {
            int pageNum = menuRequest.getPageNum() == null ? 1 : menuRequest.getPageNum();
            int pageSize = menuRequest.getPageSize() == null ? 10 : menuRequest.getPageSize();
            Page<Menu> page = new Page<>(pageNum, pageSize);
            menuService.page(page,new LambdaQueryWrapper<Menu>()
                    .like(StringUtils.isNotBlank(menuRequest.getName()), Menu::getName, menuRequest.getName())
                    .like(StringUtils.isNotBlank(menuRequest.getCode()), Menu::getCode, menuRequest.getCode()));
            return Result.success("查询成功",page);
        }
        return menuService.searchMenu(menuRequest,roleChildIds);
    }

    @Operation(summary = "新增菜单")
    @PostMapping("saveMenu")
    public Result<?> saveMenu(@RequestBody MenuRequest menuRequest) {
        try {
            parameterValidation(menuRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        Menu menu = new Menu(){{
            setName(menuRequest.getName());
            setUrl(menuRequest.getUrl());
            setCode(menuRequest.getCode());
            setIsEnable(CommonConstant.IS_ENABLE);
            setIsDelete(CommonConstant.NOT_DELETE);
            setType(menuRequest.getType());
        }};
        menuService.save(menu);
        return Result.success("新增成功");
    }

    @Operation(summary = "更新菜单")
    @PostMapping("updateMenu")
    public Result<?> updateMenu(@RequestBody MenuRequest menuRequest) {
        try {
            parameterValidation(menuRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        Menu menu = new Menu(){{
            setId(menuRequest.getId());
            setName(menuRequest.getName());
            setUrl(menuRequest.getUrl());
            setCode(menuRequest.getCode());
            setIsEnable(CommonConstant.IS_ENABLE);
            setIsDelete(CommonConstant.NOT_DELETE);
            setType(menuRequest.getType());
        }};
        menuService.updateById(menu);
        return Result.success("更新成功");
    }


    /**
     * 删除菜单
     */
    @Operation(summary = "删除菜单")
    @DeleteMapping("/deleteMenu")
    public Result<?> deleteMenu(@RequestParam("menuId") String menuId) {
        menuService.removeById(menuId);
        return Result.success("删除成功");
    }


    /**
     * 参数验证
     */
    public void parameterValidation(MenuRequest request){
        if (StringUtils.isBlank(request.getCode())) {
            throw new RuntimeException("编码不能为空");
        }
        if (StringUtils.isBlank(request.getType())) {
            throw new RuntimeException("类型不能为空");
        }
        Menu one = menuService.getOne(new LambdaQueryWrapper<Menu>().eq(Menu::getCode, request.getCode()),false);
        if (!ObjectUtils.isEmpty(one)) {
            throw new RuntimeException("编码重复");
        }
    }
}
