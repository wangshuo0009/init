package com.sg.bjftviewprotect.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.entity.Role;
import com.sg.bjftviewprotect.mapper.RoleMapper;
import com.sg.bjftviewprotect.request.RoleRequest;
import com.sg.bjftviewprotect.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Result<?> searchRole(RoleRequest roleRequest, List<String> roleChildIds) {
        int pageNum = roleRequest.getPageNum() == null ? 1 : roleRequest.getPageNum();
        int pageSize = roleRequest.getPageSize() == null ? 10 : roleRequest.getPageSize();
        Page<Role> page = new Page<>(pageNum,pageSize);
        return Result.success("查询成功",roleMapper.selectRole(page,roleRequest,roleChildIds));
    }



}
