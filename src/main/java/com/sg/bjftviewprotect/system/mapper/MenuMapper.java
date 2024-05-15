package com.sg.bjftviewprotect.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.bjftviewprotect.system.request.MenuRequest;
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
public interface MenuMapper extends BaseMapper<Menu> {

    Page<Menu> selectMenu(@Param("page") Page<Menu> page, @Param("menuRequest") MenuRequest menuRequest, @Param("roleChildIds") List<String> roleChildIds);

    List<Menu> selectAllByType(@Param("type") Integer type);
}
