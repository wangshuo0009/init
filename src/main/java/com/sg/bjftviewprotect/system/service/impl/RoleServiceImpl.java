package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.Role;
import com.sg.bjftviewprotect.system.entity.RoleMenu;
import com.sg.bjftviewprotect.system.entity.UserRole;
import com.sg.bjftviewprotect.system.mapper.RoleMapper;
import com.sg.bjftviewprotect.system.request.RoleRequest;
import com.sg.bjftviewprotect.system.service.RoleMenuService;
import com.sg.bjftviewprotect.system.service.RoleService;
import com.sg.bjftviewprotect.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public Result<?> searchRole(RoleRequest roleRequest, String userId) {
        List<String> roleChildIds = userRoleService.searchRoleChildIds(userId);
        Page<Role> page = new Page<>(roleRequest.getPageNum(),roleRequest.getPageSize());
        return Result.success("查询成功",roleMapper.selectRole(page,roleRequest,roleChildIds));
    }

    @Override
    public Result<?> saveRole(RoleRequest roleRequest, String userId) {
        Role role = new Role() {{
            setName(roleRequest.getName());
            setCode(roleRequest.getCode());
            setRemark(roleRequest.getRemark());
            setIsEnable(roleRequest.getIsEnable() == null ? 1 : roleRequest.getIsEnable());
            setIsDelete(CommonConstant.NOT_DELETE);
            setCreateTime(LocalDateTime.now());
        }};
        roleMapper.insert(role);
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
        return Result.success("新增成功");
    }

    @Override
    public Result<?> updateRole(RoleRequest roleRequest) {
        Role role = new Role() {{
            setId(roleRequest.getId());
            setName(roleRequest.getName());
            setCode(roleRequest.getCode());
            setRemark(roleRequest.getRemark());
            setIsEnable(roleRequest.getIsEnable());
        }};
        roleMapper.updateById(role);
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
        return Result.success("更新成功");
    }


}
