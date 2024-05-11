package com.sg.bjftviewprotect.controller;

import com.sg.bjftviewprotect.common.Result;
import com.sg.bjftviewprotect.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 13:31:56
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/searchRole")
    public Result<?> searchRole() {
        return Result.success("查询成功",roleService.list());
    }

}
