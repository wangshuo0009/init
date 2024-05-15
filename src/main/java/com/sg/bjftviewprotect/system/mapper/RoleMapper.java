package com.sg.bjftviewprotect.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.Role;
import com.sg.bjftviewprotect.system.request.RoleRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    Page<Role> selectRole(@Param("page")Page<Role> page, @Param("roleRequest") RoleRequest roleRequest, @Param("roleChildIds") List<String> roleChildIds);

}
