package com.sg.bjftviewprotect.system.controllerDate;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OperationsDataController
 * @Author wangshuo
 * @Date 2024/5/30 08:32
 * @Version 1.0
 **/
@RestController
@RequestMapping("/operationsData")
@Tag(name = "智慧运维")
@Log4j2
public class OperationsDataController {
    @Autowired
    CommonDataController commonDataController;

    // 数据库配置了，吊通用接口
    //@Operation(summary = "用户统计查询")
    //@PostMapping("/searchPowerUserInfo")
    //public Result<Page<PowerUserInfo>> searchPowerUserInfo(@RequestBody PowerUserInfoRequest powerUserInfoRequest) {
    //    PageUtil.initPage(powerUserInfoRequest);
    //    return commonDataController.searchPowerUserInfo(powerUserInfoRequest);
    //}
    //
    //@Operation(summary = "新增用户")
    //@PostMapping("/saveOrUpdatePowerUserInfo")
    //public Result<?> saveOrUpdatePowerUserInfo(@RequestBody PowerUserInfoRequest powerUserInfoRequest) {
    //    return commonDataController.saveOrUpdatePowerUserInfo(powerUserInfoRequest);
    //}
    //
    //
    //@Operation(summary = "用户统计查询")
    //@DeleteMapping("/deletePowerUserInfo/{id}")
    //public Result<?> deletePowerUserInfo(@PathVariable("id") String id) {
    //    return commonDataController.deletePowerUserInfo(id);
    //}

    /**
     * TODO 日负荷曲线
     */


}
