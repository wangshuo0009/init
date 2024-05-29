package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.entity.ColumnConfig;
import com.sg.bjftviewprotect.system.entity.Columns;
import com.sg.bjftviewprotect.system.mapper.ColumnConfigMapper;
import com.sg.bjftviewprotect.system.service.ColumnConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/29 14:16:02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ColumnConfigServiceImpl extends ServiceImpl<ColumnConfigMapper, ColumnConfig> implements ColumnConfigService {

    @Autowired
    private ColumnConfigMapper columnConfigMapper;

    @Override
    public List<Columns> searchColumn(String tableName) {
        return columnConfigMapper.selectColumnList(tableName);
    }

}
