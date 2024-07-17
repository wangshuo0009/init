package com.sg.init.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.init.service.UserService;
import com.sg.init.entity.User;
import com.sg.init.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
