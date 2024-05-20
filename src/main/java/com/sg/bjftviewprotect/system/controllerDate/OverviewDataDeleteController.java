package com.sg.bjftviewprotect.system.controllerDate;

import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.service.AreaUserCountService;
import com.sg.bjftviewprotect.system.service.PowerGridViewService;
import com.sg.bjftviewprotect.system.service.RegionalIntroductionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OverviewDataDeleteController
 * @Author wangshuo
 * @Date 2024/5/20 10:07
 * @Version 1.0
 **/
@RestController
@RequestMapping("/overviewData/delete")
@Tag(name = "资产概览数据删除")
public class OverviewDataDeleteController {
    @Autowired
    private RegionalIntroductionService regionalIntroductionService;
    @Autowired
    private AreaUserCountService areaUserCountService;
    @Autowired
    private PowerGridViewService powerGridViewService;

    @Operation(summary = "北京丰台区域简介新增和更新接口", description = "新增和更新接口，逻辑删除，会保留历史")
    @DeleteMapping("/regionalIntroduction/{id}")
    public Result<?> insertOrUpdateRegionalIntroduction(@PathVariable("id") String id) {
        regionalIntroductionService.removeById(id);
        return Result.success("操作成功");
    }


    @Operation(summary = "区域负荷统计新增和更新接口")
    @DeleteMapping("/areaLoadCount/{id}")
    public Result<?> areaLoadCount(@PathVariable("id") String id) {
        return Result.success("操作成功");
    }

    @Operation(summary = "区域用户统计新增和更新接口")
    @DeleteMapping("/areaUserCount/{id}")
    public Result<?> areaUserCount(@PathVariable("id") String id) {
        areaUserCountService.removeById(id);
        return Result.success("操作成功");
    }

    @Operation(summary = "区域用电量统计新增和更新接口")
    @DeleteMapping("/areaElectricityCount/{id}")
    public Result<?> areaElectricityCount(@PathVariable("id") String id) {

        return Result.success("操作成功");
    }


    @Operation(summary = "主动运维清单统计新增和更新接口")
    @DeleteMapping("/operationMaintenance/{id}")
    public Result<?> operationMaintenance(@PathVariable("id") String id) {

        return Result.success("操作成功");
    }


    @Operation(summary = "故障告警监测新增和更新接口")
    @DeleteMapping("/alarmMonitoring/{id}")
    public Result<?> alarmMonitoring(@PathVariable("id") String id) {

        return Result.success("操作成功");
    }


    @Operation(summary = "区域天气环境新增和更新接口")
    @DeleteMapping("/weather/{id}")
    public Result<?> weather(@PathVariable("id") String id) {

        return Result.success("操作成功");
    }

    @Operation(summary = "电网概览统计新增和更新接口")
    @DeleteMapping("/powerGridView/{id}")
    public Result<?> powerGridView(@PathVariable("id") String id) {
        powerGridViewService.removeById(id);
        return Result.success("操作成功");
    }

}
