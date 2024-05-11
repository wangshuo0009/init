package com.sg.bjftviewprotect.service;

import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
public interface UserService extends IService<User> {
    Result<?> searchUser(String id, String name, String roleId);

}
