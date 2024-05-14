package com.sg.bjftviewprotect.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.entity.User;
import com.sg.bjftviewprotect.mapper.UserMapper;
import com.sg.bjftviewprotect.request.UserRequest;
import com.sg.bjftviewprotect.service.UserService;
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
 * @since 2024/05/09 13:31:56
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<?> searchUser(UserRequest userRequest, String userId) {
        int pageNum = userRequest.getPageNum() == null ? 1 : userRequest.getPageNum();
        int pageSize = userRequest.getPageSize() == null ? 10 : userRequest.getPageSize();
        Page<User> page = new Page<>(pageNum,pageSize);
        List<String> userChildIds = new ArrayList<>();
        if (StringUtils.isBlank(userRequest.getId())){
            userChildIds = userMapper.selectUserChildIds(userId);
        }
        return Result.success("查询成功", userMapper.selectAllUser(page,userRequest,userChildIds));
    }
}
