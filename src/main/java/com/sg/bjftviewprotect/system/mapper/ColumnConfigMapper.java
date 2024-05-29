package com.sg.bjftviewprotect.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sg.bjftviewprotect.system.entity.ColumnConfig;
import com.sg.bjftviewprotect.system.entity.Columns;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/29 14:16:02
 */
@Mapper
public interface ColumnConfigMapper extends BaseMapper<ColumnConfig> {
    List<Columns> selectColumnList(@Param("tableName") String tableName);

}
