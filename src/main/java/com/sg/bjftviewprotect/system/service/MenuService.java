package com.sg.bjftviewprotect.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.entity.Menu;
import com.sg.bjftviewprotect.system.request.MenuRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
public interface MenuService extends IService<Menu> {

    Page<Menu> searchMenu(MenuRequest menuRequest, String roleChildIds);

    Object searchAllByType(Integer type);
}
