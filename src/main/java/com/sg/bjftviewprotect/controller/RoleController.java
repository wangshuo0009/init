package com.sg.bjftviewprotect.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sg.bjftviewprotect.annotation.LoginVerification;
import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.constant.CommonConstant;
import com.sg.bjftviewprotect.entity.Role;
import com.sg.bjftviewprotect.entity.RoleMenu;
import com.sg.bjftviewprotect.entity.UserRole;
import com.sg.bjftviewprotect.request.RoleRequest;
import com.sg.bjftviewprotect.service.RoleMenuService;
import com.sg.bjftviewprotect.service.RoleService;
import com.sg.bjftviewprotect.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private UserRoleService userRoleService;

    @Operation(summary = "查询所有角色信息下拉框", tags = "用户管理")
    @GetMapping("/searchAllRole")
    public Result<?> searchAllRole() {
        List<Role> list = roleService.list();
        return Result.success("查询成功",list);
    }


    @Operation(summary = "新增用户角色下拉框", tags = "用户管理")
    @GetMapping("/searchRole")
    public Result<?> searchRole(@CookieValue(value = CommonConstant.X_USER_ID) String userId) {
        List<String> roleChildIds = userRoleService.searchRoleChildIds(userId);
        RoleRequest roleRequest = new RoleRequest(){{
            setPageNum(1);
            setPageSize(100);
        }};
        return roleService.searchRole(roleRequest,roleChildIds);
    }


    @Operation(summary = "查询角色信息")
    @PostMapping("/searchRole")
    public Result<?> searchRole(@RequestBody RoleRequest roleRequest,
                                @CookieValue(value = CommonConstant.X_USER_ID) String userId) {
        List<String> roleChildIds = userRoleService.searchRoleChildIds(userId);
        return roleService.searchRole(roleRequest,roleChildIds);
    }

    @Operation(summary = "新增角色信息")
    @PostMapping("/saveRole")
    @Transactional(rollbackFor = Exception.class)
    public Result<?> saveRole(@RequestBody RoleRequest roleRequest,
                              @CookieValue(value = CommonConstant.X_USER_ID) String userId) {
        try {
            parameterValidation(roleRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        Role role = new Role() {{
            setName(roleRequest.getName());
            setCode(roleRequest.getCode());
            setRemark(roleRequest.getRemark());
            setIsEnable(roleRequest.getIsEnable() == null ? 1 : roleRequest.getIsEnable());
            setIsDelete(CommonConstant.NOT_DELETE);
            setCreateTime(LocalDateTime.now());
        }};
        roleService.save(role);
        // 新增完成后立即保存到该用户
        userRoleService.save(new UserRole(){{
            setRoleId(role.getId());
            setUserId(userId);
        }});
        if (!ObjectUtils.isEmpty(roleRequest.getMenuId())){
            List<String> menuIdList = roleRequest.getMenuId();
            List<RoleMenu> roleMenus = new ArrayList<>();
            for (String menuId : menuIdList){
                roleMenus.add(new RoleMenu(){{
                    setRoleId(role.getId());
                    setMenuId(menuId);
                }});
            }
            roleMenuService.saveBatch(roleMenus);
        }
        return Result.success("操作成功");
    }

    /**
     * 更新用户信息
     */
    @Operation(summary = "更新角色信息")
    @PostMapping("/updateRole")
    @Transactional(rollbackFor = Exception.class)
    public Result<?> updateRole(@RequestBody RoleRequest roleRequest) {
        try {
            parameterValidation(roleRequest);
        }catch (Exception e){
            return Result.fail(e.getMessage());
        }
        Role role = new Role() {{
            setId(roleRequest.getId());
            setName(roleRequest.getName());
            setCode(roleRequest.getCode());
            setRemark(roleRequest.getRemark());
            setIsEnable(roleRequest.getIsEnable());
        }};
        roleService.updateById(role);
        if (!ObjectUtils.isEmpty(roleRequest.getMenuId())){
            List<String> menuIdList = roleRequest.getMenuId();
            List<RoleMenu> roleMenus = new ArrayList<>();
            roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleRequest.getId()));
            for (String menuId : menuIdList){
                roleMenus.add(new RoleMenu(){{
                    setRoleId(role.getId());
                    setMenuId(menuId);
                }});
            }
            roleMenuService.saveBatch(roleMenus);
        } else {
            roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleRequest.getId()));
        }
        return Result.success("操作成功");
    }



    /**
     * 删除角色信息
     */
    @Operation(summary = "删除角色信息")
    @DeleteMapping("/deleteRole")
    public Result<?> deleteRole(@RequestParam(value = "roleId") String roleId) {
        roleService.removeById(roleId);
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
