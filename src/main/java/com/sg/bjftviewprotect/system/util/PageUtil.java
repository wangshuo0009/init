package com.sg.bjftviewprotect.system.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.constant.CommonConstant;

import java.lang.reflect.Method;

/**
 * @ClassName PageUtil
 * @Author wangshuo
 * @Date 2024/5/19 19:49
 * @Version 1.0
 **/
public class PageUtil {
    // 每页数据大小属性名
    private static final String pageSizeStr = "pageSize";
    // 页码属性名
    private static final String pageCurrentStr = "pageNum";
    // 默认页码
    private static final int DEFAULT_PAGE_NUM = CommonConstant.DEFAULT_PAGE_NUM;
    // 默认每页数据大小
    private static final int DEFAULT_PAGE_SIZE = CommonConstant.DEFAULT_PAGE_SIZE;
    // 默认数据大小，一般用于下拉框
    private static final int DEFAULT_PAGE_FOR_LIST_SIZE = CommonConstant.DEFAULT_PAGE_FOR_LIST_SIZE;

    /**
     * 自定义封装 ，一般用于展示下拉框数据
     * 第 1 页，100 条数据
     */
    public static <T> Page<T> createPageForList() {
        return new Page<>(1, DEFAULT_PAGE_FOR_LIST_SIZE);
    }
    /**
     * 自定义封装 ，一般用于展示下拉框数据
     * 需要传入请求体
     * return 第 1 页，100 条数据
     */
    public static <T> T pageForList(T request) {
        return setPageAttributes(request, 1, DEFAULT_PAGE_FOR_LIST_SIZE);
    }

    /**
     * 自定义封装 ，一般用于初始化页码
     * 需要传入请求体
     */
    public static <T> T initPage(T request) {
        try {
            Integer pageCurrent = (Integer) getMethodValue(request, pageCurrentStr);
            Integer pageSize = (Integer) getMethodValue(request, pageSizeStr);

            int finalPageCurrent = (pageCurrent == null || pageCurrent < 1) ? DEFAULT_PAGE_NUM : pageCurrent;
            int finalPageSize = (pageSize == null || pageSize < 1) ? DEFAULT_PAGE_SIZE : pageSize;

            return setPageAttributes(request, finalPageCurrent, finalPageSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize page request", e);
        }
    }




    private static <T> T setPageAttributes(T request, int pageCurrent, int pageSize) {
        try {
            setMethodValue(request, pageCurrentStr, pageCurrent);
            setMethodValue(request, pageSizeStr, pageSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set page attributes", e);
        }
        return request;
    }

    private static <T> Object getMethodValue(T request, String attributeName) throws Exception {
        String methodName = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
        Method method = request.getClass().getMethod(methodName);
        return method.invoke(request);
    }

    private static <T> void setMethodValue(T request, String attributeName, Object value) throws Exception {
        String methodName = "set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
        Method method = request.getClass().getMethod(methodName, value.getClass());
        method.invoke(request, value);
    }

}
