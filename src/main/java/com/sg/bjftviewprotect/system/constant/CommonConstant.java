package com.sg.bjftviewprotect.system.constant;

/**
 * @ClassName CommonConstant
 * @Author wangshuo
 * @Date 2024/5/13 14:51
 * @Version 1.0
 **/
public class CommonConstant {
    // 删除
    public static final Integer IS_DELETE = 1;
    // 未删除
    public static final Integer NOT_DELETE = 0;
    // 启用
    public static final Integer IS_ENABLE = 1;
    // 禁用
    public static final Integer NOT_ENABLE = 0;

    // 用户id
    public static final String X_USER_ID = "X-User-ID";
    // 用户帐号
    public static final String X_USER_ACCOUNT = "X-User-Account";

    // 用户帐号
    public static final String X_Token = "X-Token";

    // 三维代码
    public static final Integer THREE_DIMENSIONAL_TYPE_CODE = 1;
    // 系统管理代码
    public static final Integer MANAGER_SYSTEM_TYPE_CODE = 2;

    // 默认每页大小
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    // 默认页码
    public static final Integer DEFAULT_PAGE_NUM = 1;
    // 默认分页列表大小
    public static final Integer DEFAULT_PAGE_FOR_LIST_SIZE = 100;
    // 高压
    public static final Integer HIGH_VOLTAGE = 1;
    // 低压
    public static final Integer LOW_VOLTAGE = 2;


    // 重要用户
    public static final String USER_IMPORTANT = "级重要用户";
    // 非重要用户
    public static final String USER_NOT_IMPORTANT = "非重要用户";

    // 高压用户
    public static final String USER_HIGH_VOLTAGE = "高压";
    // 低压用户
    public static final String USER_LOW_VOLTAGE = "低压";


}
