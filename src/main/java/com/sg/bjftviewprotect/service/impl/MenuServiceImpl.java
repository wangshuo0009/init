package com.sg.bjftviewprotect.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.entity.Menu;
import com.sg.bjftviewprotect.mapper.MenuMapper;
import com.sg.bjftviewprotect.request.MenuRequest;
import com.sg.bjftviewprotect.service.MenuService;
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
 * @since 2024/05/09 13:31:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Result<?> searchMenu(MenuRequest menuRequest, List<String> roleChildIds) {
        int pageNum = menuRequest.getPageNum() == null ? 1 : menuRequest.getPageNum();
        int pageSize = menuRequest.getPageSize() == null ? 10 : menuRequest.getPageSize();
        Page<Menu> page = new Page<>(pageNum,pageSize);
        return Result.success("查询成功",menuMapper.selectMenu(page,menuRequest,roleChildIds));
    }

    public Result<?> searchAllByType(Integer type) {
        return Result.success("查询成功",menuMapper.selectAllByType(type));
    }


}
