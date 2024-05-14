package com.sg.bjftviewprotect.controller;

import com.sg.bjftviewprotect.service.RoleService;
import com.sg.bjftviewprotect.service.UserRoleService;
import com.sg.bjftviewprotect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/11 11:45:34
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    //public List<String> userRoleController(String account) {
    //    User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getAccount, account));
    //    roleService.list();
    //    List<UserRole> userRoles = userRoleService.list(new LambdaQueryWrapper<UserRole>()
    //            .eq(UserRole::getUserId, one.getId())
    //            .select(UserRole::getRoleId)
    //    );
    //
    //}

}
