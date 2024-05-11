package com.sg.bjftviewprotect.mapper;

import com.sg.bjftviewprotect.entity.User;
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
 * @since 2024/05/09 13:31:56
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAllUser(@Param("id") String id, @Param("name") String name, @Param("roleId") String roleId);
}
