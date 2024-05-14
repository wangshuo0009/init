package com.sg.bjftviewprotect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.entity.User;
import com.sg.bjftviewprotect.request.UserRequest;
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
    Page<User> selectAllUser(@Param("page") Page<User> page, @Param("userRequest")UserRequest userRequest, @Param("userChildIds") List<String> userChildIds);

    // 查询当前用户子账户
    List<String> selectUserChildIds(@Param("userId") String userId);
}
