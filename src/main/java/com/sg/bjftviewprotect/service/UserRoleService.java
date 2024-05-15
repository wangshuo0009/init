package com.sg.bjftviewprotect.service;

import com.sg.bjftviewprotect.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/11 11:45:34
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     * 根据用户id查询其角色及子角色id
     */
    List<String> searchRoleChildIds(String userId);
}
