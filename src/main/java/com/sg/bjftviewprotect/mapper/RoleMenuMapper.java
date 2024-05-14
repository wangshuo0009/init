package com.sg.bjftviewprotect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.bjftviewprotect.entity.Menu;
import com.sg.bjftviewprotect.entity.RoleMenu;
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
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Menu> selectRoleMenu(@Param("roleId") String roleId);

}
