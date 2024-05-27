package com.sg.bjftviewprotect.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.AreaElectricityCount;
import com.sg.bjftviewprotect.system.request.AreaElectricityCountRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/27 10:18:30
 */
@Mapper
public interface AreaElectricityCountMapper extends BaseMapper<AreaElectricityCount> {

    Page<AreaElectricityCount> selectAreaElectricityCount(@Param("page")Page<AreaElectricityCount> page, @Param("areaElectricityCountRequest") AreaElectricityCountRequest areaElectricityCountRequest);

    List<AreaElectricityCount> selectAreaElectricityCountForView();
}
