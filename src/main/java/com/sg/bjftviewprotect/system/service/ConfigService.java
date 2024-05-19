package com.sg.bjftviewprotect.system.service;

import com.sg.bjftviewprotect.system.entity.Config;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/17 11:20:58
 */
public interface ConfigService extends IService<Config> {

    Config getSuperAdmin();

}
