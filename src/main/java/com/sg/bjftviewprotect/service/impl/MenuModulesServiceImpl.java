package com.sg.bjftviewprotect.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.entity.MenuModules;
import com.sg.bjftviewprotect.mapper.MenuModulesMapper;
import com.sg.bjftviewprotect.service.MenuModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/15 16:46:05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuModulesServiceImpl extends ServiceImpl<MenuModulesMapper, MenuModules> implements MenuModulesService {

    @Autowired
    private MenuModulesMapper menuModulesMapper;

    //public List<MenuModules> searchMenuByType(String type) {
    //    return menuModulesMapper.selectByMenuId();
    //}


}
