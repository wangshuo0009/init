package com.sg.bjftviewprotect.system.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.Role;
import com.sg.bjftviewprotect.system.entity.User;
import com.sg.bjftviewprotect.system.entity.UserRole;
import com.sg.bjftviewprotect.system.mapper.UserMapper;
import com.sg.bjftviewprotect.system.request.UserRequest;
import com.sg.bjftviewprotect.system.service.RoleService;
import com.sg.bjftviewprotect.system.service.UserRoleService;
import com.sg.bjftviewprotect.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;

    @Override
    public Result<?> searchUser(UserRequest userRequest, String userId) {
        Page<User> page = new Page<>(userRequest.getPageNum(),userRequest.getPageSize());
        List<String> userChildIds = userMapper.selectUserChildIds(userId);
        Page<User> userPage = userMapper.selectAllUser(page, userRequest, userChildIds);
        if (!userPage.getRecords().isEmpty()) {
            // 查询所有的 UserRole 和 Role 数据
            List<UserRole> userRoleList = userRoleService.list();
            List<Role> roleList = roleService.list();

            // 将 UserRole 按照 userId 分组
            Map<String, List<UserRole>> userRoleMap = userRoleList.stream()
                    .collect(Collectors.groupingBy(UserRole::getUserId));

            // 将 Role 按照 roleId 分组
            Map<String, Role> roleMap = roleList.stream()
                    .collect(Collectors.toMap(Role::getId, role -> role));

            // 将角色数据绑定到相应的 User 对象中
            userPage.getRecords().forEach(user -> {
                List<UserRole> userRoles = userRoleMap.get(user.getId());
                if (!userRoles.isEmpty()) {
                    user.setRoles(userRoles.stream()
                            .map(userRole -> roleMap.get(userRole.getRoleId()))
                            .collect(Collectors.toList())
                    );
                }
            });
        }
        return Result.success("查询成功", userPage);
    }

    @Override
    public Result<?> saveUser(UserRequest userRequest, String userId) {
        User user = new User() {{
            setName(userRequest.getName());
            setPassword(StringUtils.isBlank(userRequest.getPassword()) ? null : MD5.create().digestHex(userRequest.getPassword()));
            setAccount(userRequest.getAccount());
            setRemark(userRequest.getRemark());
            setIsEnable(userRequest.getIsEnable() == null ? 1 : userRequest.getIsEnable());
            setIsDelete(CommonConstant.NOT_DELETE);
            setParentId(userId);
            setCreateTime(LocalDateTime.now());
        }};
        userMapper.insert(user);
        if (!ObjectUtils.isEmpty(userRequest.getRoleId())){
            List<String> roleIdList = userRequest.getRoleId();
            List<UserRole> userRoles = new ArrayList<>();
            for (String roleId : roleIdList){
                userRoles.add(new UserRole(){{
                    setUserId(user.getId());
                    setRoleId(roleId);
                }});
            }
            userRoleService.saveBatch(userRoles);
        }
        return Result.success("新增成功");
    }

    @Override
    public Result<?> updateUser(UserRequest userRequest) {
        User user = new User() {{
            setId(userRequest.getId());
            setName(userRequest.getName());
            setPassword(StringUtils.isBlank(userRequest.getPassword()) ? null : MD5.create().digestHex(userRequest.getPassword()));
            setAccount(userRequest.getAccount());
            setRemark(userRequest.getRemark());
            setIsEnable(userRequest.getIsEnable());
        }};
        userMapper.updateById(user);
        if (!ObjectUtils.isEmpty(userRequest.getRoleId())){
            List<String> roleIdList = userRequest.getRoleId();
            List<UserRole> userRoles = new ArrayList<>();
            // 移除当前用户角色中间表信息
            userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
            for (String roleId : roleIdList){
                userRoles.add(new UserRole(){{
                    setUserId(user.getId());
                    setRoleId(roleId);
                }});
            }
            userRoleService.saveBatch(userRoles);
        } else {
            userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
        }
        return Result.success("更新成功");
    }
}
