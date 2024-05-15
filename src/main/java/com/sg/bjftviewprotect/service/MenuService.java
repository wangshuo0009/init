package com.sg.bjftviewprotect.service;

import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bjftviewprotect.request.MenuRequest;

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
}
