package com.sg.bjftviewprotect.system.service;

import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.system.request.MenuRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
public interface MenuService extends IService<Menu> {

    Result<?> searchMenu(MenuRequest menuRequest, List<String> roleChildIds);

    Result<?> searchAllByType(Integer type);
}
