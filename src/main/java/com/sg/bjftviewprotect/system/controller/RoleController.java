package com.sg.bjftviewprotect.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.annotation.LoginVerification;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.Role;
import com.sg.bjftviewprotect.system.request.RoleRequest;
import com.sg.bjftviewprotect.system.service.RoleService;
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
 * @since 2024/05/09 13:31:56
 */
@RestController
@RequestMapping("/role")
@Tag(name = "角色管理")
@LoginVerification
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Operation(summary = "用户管理-角色查询条件列表", tags = "用户管理")
    @GetMapping("/searchAllRole")
    public Result<?> searchAllRole() {
        Page<Role> page = PageUtil.createPageForList();
        roleService.page(page);
        return Result.success("查询成功",page);
    }


    @Operation(summary = "用户管理-新增用户角色列表", tags = "用户管理")
    @GetMapping("/searchRole")
    public Result<?> searchRole(@CookieValue(value = CommonConstant.X_USER_ID) String userId) {
        RoleRequest roleRequest = PageUtil.pageForList(new RoleRequest());
        return roleService.searchRole(roleRequest,userId);
    }


    @Operation(summary = "查询角色信息")
    @PostMapping("/searchRole")
    public Result<?> searchRole(@RequestBody RoleRequest roleRequest,
                                @CookieValue(value = CommonConstant.X_USER_ID) String userId) {
        PageUtil.initPage(roleRequest);
        return roleService.searchRole(roleRequest,userId);
    }

    @Operation(summary = "新增角色信息")
    @PostMapping("/saveRole")
    public Result<?> saveRole(@RequestBody RoleRequest roleRequest,
                              @CookieValue(value = CommonConstant.X_USER_ID) String userId) {
        try {
            parameterValidation(roleRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        return roleService.saveRole(roleRequest, userId);
    }

    /**
     * 更新用户信息
     */
    @Operation(summary = "更新角色信息")
    @PostMapping("/updateRole")
    public Result<?> updateRole(@RequestBody RoleRequest roleRequest) {
        try {
            parameterValidation(roleRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        return roleService.updateRole(roleRequest);
    }



    /**
     * 删除角色信息
     */
    @Operation(summary = "删除角色信息")
    @DeleteMapping("/deleteRole/{id}")
    public Result<?> deleteRole(@PathVariable("id") String id) {
        roleService.removeById(id);
        return Result.success("删除成功");
    }


    /**
     * 参数验证
     */
    public void parameterValidation(RoleRequest request){
        if (StringUtils.isBlank(request.getCode())) {
            throw new RuntimeException("编码不能为空");
        }
        Role one = roleService.getOne(new LambdaQueryWrapper<Role>().eq(Role::getCode, request.getCode()),false);
        if (!ObjectUtils.isEmpty(one)) {
            throw new RuntimeException("编码重复");
        }
    }

}
