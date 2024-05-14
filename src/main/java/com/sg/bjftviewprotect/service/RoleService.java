package com.sg.bjftviewprotect.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.entity.Role;
import com.sg.bjftviewprotect.request.RoleRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
public interface RoleService extends IService<Role> {
    Result<?> searchRole(RoleRequest roleRequest, List<String> roleChildIds);
}
