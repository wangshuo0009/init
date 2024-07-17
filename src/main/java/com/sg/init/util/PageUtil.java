package com.sg.init.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.init.constant.CommonConstant;

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
    public static <T> T pageForList(T clazz) {
        return setPageAttributes(clazz, 1, DEFAULT_PAGE_FOR_LIST_SIZE);
    }

    /**
     * 自定义封装 ，一般用于初始化页码
     * 需要传入请求体
     */
    public static <T> void initPage(T clazz) {
        try {
            Integer pageCurrent = (Integer) getMethodValue(clazz, pageCurrentStr);
            Integer pageSize = (Integer) getMethodValue(clazz, pageSizeStr);

            int finalPageCurrent = (pageCurrent == null || pageCurrent < 1) ? DEFAULT_PAGE_NUM : pageCurrent;
            int finalPageSize = (pageSize == null || pageSize < 1) ? DEFAULT_PAGE_SIZE : pageSize;

            setPageAttributes(clazz, finalPageCurrent, finalPageSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize page clazz", e);
        }
    }

    public static <T> Page<T> initPageToPage(T clazz) {
        try {
            Integer pageCurrent = (Integer) getMethodValue(clazz, pageCurrentStr);
            Integer pageSize = (Integer) getMethodValue(clazz, pageSizeStr);

            int finalPageCurrent = (pageCurrent == null || pageCurrent < 1) ? DEFAULT_PAGE_NUM : pageCurrent;
            int finalPageSize = (pageSize == null || pageSize < 1) ? DEFAULT_PAGE_SIZE : pageSize;

            return getPage(clazz, finalPageCurrent, finalPageSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize page clazz", e);
        }
    }



    private static <T> T setPageAttributes(T clazz, int pageCurrent, int pageSize) {
        try {
            setMethodValue(clazz, pageCurrentStr, pageCurrent);
            setMethodValue(clazz, pageSizeStr, pageSize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set page attributes", e);
        }
        return clazz;
    }

    private static <T> Page<T> getPage(T clazz, int pageCurrent, int pageSize) {
        try {
            setMethodValue(clazz, pageCurrentStr, pageCurrent);
            setMethodValue(clazz, pageSizeStr, pageSize);

            return new Page<>((Integer) getMethodValue(clazz, pageCurrentStr), (Integer) getMethodValue(clazz, pageSizeStr));
        } catch (Exception e) {
            throw new RuntimeException("Failed to set page attributes", e);
        }
    }

    private static <T> Object getMethodValue(T clazz, String attributeName) throws Exception {
        String methodName = "get" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
        Method method = clazz.getClass().getMethod(methodName);
        return method.invoke(clazz);
    }

    private static <T> void setMethodValue(T clazz, String attributeName, Object value) throws Exception {
        String methodName = "set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
        Method method = clazz.getClass().getMethod(methodName, value.getClass());
        method.invoke(clazz, value);
    }

}
