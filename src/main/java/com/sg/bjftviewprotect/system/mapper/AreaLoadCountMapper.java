package com.sg.bjftviewprotect.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.AreaLoadCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.bjftviewprotect.system.request.AreaLoadCountRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/24 10:47:39
 */
@Mapper
public interface AreaLoadCountMapper extends BaseMapper<AreaLoadCount> {

    Page<AreaLoadCount> selectAreaLoadCount(@Param("page") Page<AreaLoadCount> page, @Param("areaLoadCountRequest") AreaLoadCountRequest areaLoadCountRequest);
    List<AreaLoadCount> selectAreaLoadCountForView();
}
