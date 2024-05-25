package com.sg.bjftviewprotect.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.request.UserRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
public interface UserService extends IService<User> {
    Page<User> searchUser(UserRequest userRequest, String userId);

    int saveUser(UserRequest userRequest, String userId);

    int updateUser(UserRequest userRequest);
}
