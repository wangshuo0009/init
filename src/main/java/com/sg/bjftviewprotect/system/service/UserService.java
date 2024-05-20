package com.sg.bjftviewprotect.system.service;

import com.sg.bjftviewprotect.system.common.Result;
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
    Result<?> searchUser(UserRequest userRequest, String userId);

    Result<?> saveUser(UserRequest userRequest, String userId);

    Result<?> updateUser(UserRequest userRequest);
}
