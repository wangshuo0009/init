package com.sg.bjftviewprotect.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.entity.Role;
import com.sg.bjftviewprotect.entity.UserRole;
import com.sg.bjftviewprotect.mapper.UserRoleMapper;
import com.sg.bjftviewprotect.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> searchRoleChildIds(String userId) {
        List<Role> roles = userRoleMapper.selectUserRole(userId);
        List<String> roleIds = new ArrayList<>();
        for (Role role : roles) {
            roleIds.add(role.getId());
        }
        return  userRoleMapper.selectRoleChildIds(roleIds);
    }
}
