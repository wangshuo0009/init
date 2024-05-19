package com.sg.bjftviewprotect.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.Role;
import com.sg.bjftviewprotect.system.request.RoleRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
public interface RoleService extends IService<Role> {
    Result<?> searchRole(RoleRequest roleRequest, String roleChildIds);

    Result<?> saveRole(RoleRequest roleRequest, String userId);
    Result<?> updateRole(RoleRequest roleRequest);
}
