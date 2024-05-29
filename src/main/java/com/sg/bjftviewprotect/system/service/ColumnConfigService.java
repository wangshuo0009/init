package com.sg.bjftviewprotect.system.service;

import com.sg.bjftviewprotect.system.entity.ColumnConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.entity.Columns;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/29 14:16:02
 */
public interface ColumnConfigService extends IService<ColumnConfig> {
    List<Columns> searchColumn(String tableName);

}
