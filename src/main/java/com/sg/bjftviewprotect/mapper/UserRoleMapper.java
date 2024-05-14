package com.sg.bjftviewprotect.mapper;

import com.sg.bjftviewprotect.entity.Role;
import com.sg.bjftviewprotect.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/11 11:45:34
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    // 根据用户id，查他所属角色
    List<Role> selectUserRole(@Param("userId") String userId);

    // 根据用户id，查他所属角色和子角色
    List<String> selectRoleChildIds(@Param("roleIds") List<String> roleIds);
}
