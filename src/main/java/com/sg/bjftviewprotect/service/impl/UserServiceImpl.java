package com.sg.bjftviewprotect.service.impl;

import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.entity.User;
import com.sg.bjftviewprotect.mapper.UserMapper;
import com.sg.bjftviewprotect.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<?> searchUser(String id, String name, String roleId) {
        return Result.success("查询成功", userMapper.selectAllUser(id,name,roleId));
    }
}
