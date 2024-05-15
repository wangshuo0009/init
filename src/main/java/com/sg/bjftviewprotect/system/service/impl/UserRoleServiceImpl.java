package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.entity.Role;
import com.sg.bjftviewprotect.system.entity.UserRole;
import com.sg.bjftviewprotect.system.mapper.UserRoleMapper;
import com.sg.bjftviewprotect.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/11 11:45:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> searchRoleChildIds(String userId) {
        List<Role> roles = userRoleMapper.selectUserRole(userId);
        List<String> roleIds = new ArrayList<>();
        if (!roles.isEmpty()) {
            for (Role role : roles) {
                roleIds.add(role.getId());
            }
        }
        return  roleIds.isEmpty() ? null : userRoleMapper.selectRoleChildIds(roleIds);
    }
}
