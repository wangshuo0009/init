package com.sg.init.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.init.entity.User;
import org.apache.ibatis.annotations.Mapper;

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
}
