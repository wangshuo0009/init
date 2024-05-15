package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.User;
import com.sg.bjftviewprotect.system.mapper.UserMapper;
import com.sg.bjftviewprotect.system.request.UserRequest;
import com.sg.bjftviewprotect.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<?> searchUser(UserRequest userRequest, String userId) {
        int pageNum = userRequest.getPageNum() == null ? 1 : userRequest.getPageNum();
        int pageSize = userRequest.getPageSize() == null ? 10 : userRequest.getPageSize();
        Page<User> page = new Page<>(pageNum,pageSize);
        List<String> userChildIds = userMapper.selectUserChildIds(userId);
        return Result.success("查询成功", userMapper.selectAllUser(page,userRequest,userChildIds));
    }
}
