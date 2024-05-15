package com.sg.bjftviewprotect.system.service.impl;

import com.sg.bjftviewprotect.system.entity.RoleMenu;
import com.sg.bjftviewprotect.system.mapper.RoleMenuMapper;
import com.sg.bjftviewprotect.system.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/11 11:45:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
