package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.config.AdminConfig;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.Menu;
import com.sg.bjftviewprotect.system.mapper.MenuMapper;
import com.sg.bjftviewprotect.system.request.MenuRequest;
import com.sg.bjftviewprotect.system.service.MenuService;
import com.sg.bjftviewprotect.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Result<?> searchMenu(MenuRequest menuRequest, String userId) {
        List<String> roleChildIds = userRoleService.searchRoleChildIds(userId);
        // 超级管理员角色处理逻辑
        if (!ObjectUtils.isEmpty(roleChildIds) && roleChildIds.contains(AdminConfig.adminRole.getId())) {
            roleChildIds = null;
        }
        Page<Menu> page = new Page<>(menuRequest.getPageNum(), menuRequest.getPageSize());
        return Result.success("查询成功",menuMapper.selectMenu(page,menuRequest,roleChildIds));
    }

    public Result<?> searchAllByType(Integer type) {
        List<Menu> menus = menuMapper.selectAllByType(type);
        // 三维的路由
        if (Objects.equals(type, CommonConstant.THREE_DIMENSIONAL_TYPE_CODE)){
            return Result.success("查询成功",menus);
        } else {
            // 后台管理的路由
            Map<Integer, List<Menu>> groupedMenus = new HashMap<>();
            if (!ObjectUtils.isEmpty(menus)) {
                groupedMenus = menus.stream().collect(Collectors.groupingBy(Menu::getType));
            }
            return Result.success("查询成功",groupedMenus);
        }
    }


}
