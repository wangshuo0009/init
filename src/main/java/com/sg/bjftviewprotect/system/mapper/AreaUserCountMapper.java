package com.sg.bjftviewprotect.system.mapper;

import com.sg.bjftviewprotect.system.entity.AreaUserCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/20 09:03:26
 */
@Mapper
public interface AreaUserCountMapper extends BaseMapper<AreaUserCount> {

    List<AreaUserCount> selectAreaUserCount();
}
