package com.sg.bjftviewprotect.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.bjftviewprotect.system.entity.MenuModules;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/15 16:46:05
 */
@Mapper
public interface MenuModulesMapper extends BaseMapper<MenuModules> {

    List<MenuModules> selectByMenuId(@Param("menuId") String menuId);
}
