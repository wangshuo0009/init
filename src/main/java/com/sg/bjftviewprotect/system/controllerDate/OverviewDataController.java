package com.sg.bjftviewprotect.system.controllerDate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.AreaLoadCount;
import com.sg.bjftviewprotect.system.entity.PowerGridView;
import com.sg.bjftviewprotect.system.request.AreaLoadCountRequest;
import com.sg.bjftviewprotect.system.request.RegionalIntroductionRequest;
import com.sg.bjftviewprotect.system.service.AreaLoadCountService;
import com.sg.bjftviewprotect.system.service.AreaUserCountService;
import com.sg.bjftviewprotect.system.service.PowerGridViewService;
import com.sg.bjftviewprotect.system.service.RegionalIntroductionService;
import com.sg.bjftviewprotect.system.util.PageUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 09:30:21
 */
@RestController
@RequestMapping("/overviewData")
@Tag(name = "资产概览")
@Log4j2
public class OverviewDataController {
    @Autowired
    private RegionalIntroductionService regionalIntroductionService;
    @Autowired
    private AreaUserCountService areaUserCountService;
    @Autowired
    private PowerGridViewService powerGridViewService;
    @Autowired
    private AreaLoadCountService areaLoadCountService;

    @Operation(summary = "北京丰台区域简介新增和更新接口")
    @GetMapping("/searchRegionalIntroduction")
    public Result<?> searchRegionalIntroduction() {
        return Result.success("查询成功", regionalIntroductionService.list());
    }

    @Operation(summary = "北京丰台区域简介新增和更新接口")
    @PostMapping("/savOrUpdateRegionalIntroduction")
    public Result<?> savOrUpdateRegionalIntroduction(RegionalIntroductionRequest regionalIntroductionRequest) {
        int i = regionalIntroductionService.saveOrUpdateRegionalIntroduction(regionalIntroductionRequest);
        return Result.success("操作成功", i);
    }
    @Operation(summary = "北京丰台区域简介删除接口")
    @DeleteMapping("/deleteRegionalIntroduction/{id}")
    public Result<?> deleteRegionalIntroduction(@PathVariable("id") String id) {
        regionalIntroductionService.removeById(id);
        return Result.success("操作成功");
    }


    @Operation(summary = "区域负荷统计查询接口")
    @PostMapping("/searchAreaLoadCount")
    public Result<?> searchAreaLoadCount(@RequestBody AreaLoadCountRequest areaLoadCountRequest) {
        PageUtil.initPage(areaLoadCountRequest);
        Page<AreaLoadCount> page = areaLoadCountService.searchAreaLoadCount(areaLoadCountRequest);
        return Result.success("操作成功", page);
    }
    @Operation(summary = "区域负荷统计新增和更新接口")
    @PostMapping("/savOrUpdateAreaLoadCount")
    public Result<?> savOrUpdateAreaLoadCount(@RequestBody AreaLoadCount areaLoadCount) {
        areaLoadCount.setCreateTime(LocalDateTime.now());
        areaLoadCountService.saveOrUpdate(areaLoadCount);
        return Result.success("操作成功");
    }
    @Operation(summary = "区域负荷统计删除接口")
    @DeleteMapping("/deleteAreaLoadCount/{id}")
    public Result<?> deleteAreaLoadCount(@PathVariable("id") String id) {
        return Result.success("操作成功");
    }


    /**
     * 区域用户统计查询接口 ， 用通用数据管理 - 用户档案，新增删除 同样/overviewData/common/searchPowerUserInfo 接口
     */
    //@Operation(summary = "区域用户统计查询接口 -- " ,description = "用查询用户档案信息接口，/overviewData/common/searchPowerUserInfo 接口")
    //@PostMapping("/searchAreaUserCount")
    //public Result<?> searchAreaUserCount(@RequestBody AreaUserCount areaUserCount) {
    //    areaUserCountService.saveOrUpdate(areaUserCount);
    //    return Result.success("操作成功");
    //}

    //@Operation(summary = "区域用户统计新增和更新接口")
    //@PostMapping("/savOrUpdateAreaUserCount")
    //public Result<?> savOrUpdateAreaUserCount(@RequestBody AreaUserCount areaUserCount) {
    //    areaUserCountService.saveOrUpdate(areaUserCount);
    //    return Result.success("操作成功");
    //}
    //@Operation(summary = "区域用户统计删除接口")
    //@DeleteMapping("/deleteAreaUserCount/{id}")
    //public Result<?> deleteAreaUserCount(@PathVariable("id") String id) {
    //    areaUserCountService.removeById(id);
    //    return Result.success("操作成功");
    //}

    @Operation(summary = "区域用电量统计新增和更新接口")
    @PostMapping("/savOrUpdateAreaElectricityCount")
    public Result<?> savOrUpdateAreaElectricityCount() {

        return Result.success("操作成功");
    }
    @Operation(summary = "区域用电量统计删除接口")
    @DeleteMapping("/deleteAreaElectricityCount/{id}")
    public Result<?> deleteAreaElectricityCount(@PathVariable("id") String id) {

        return Result.success("操作成功");
    }

    @Operation(summary = "主动运维清单统计新增和更新接口")
    @PostMapping("/savOrUpdateOperationMaintenance")
    public Result<?> savOrUpdateOperationMaintenance() {

        return Result.success("操作成功");
    }
    @Operation(summary = "主动运维清单统计删除接口")
    @DeleteMapping("/deleteOperationMaintenance/{id}")
    public Result<?> deleteOperationMaintenance(@PathVariable("id") String id) {

        return Result.success("操作成功");
    }

    @Operation(summary = "故障告警监测新增和更新接口")
    @PostMapping("/savOrUpdateAlarmMonitoring")
    public Result<?> savOrUpdateAlarmMonitoring() {

        return Result.success("操作成功");
    }
    @Operation(summary = "故障告警监测删除接口")
    @DeleteMapping("/deleteAlarmMonitoring/{id}")
    public Result<?> deleteAlarmMonitoring(@PathVariable("id") String id) {

        return Result.success("操作成功");
    }

    @Operation(summary = "区域天气环境新增和更新接口")
    @PostMapping("/savOrUpdateWeather")
    public Result<?> savOrUpdateWeather() {

        return Result.success("操作成功");
    }
    @Operation(summary = "区域天气环境删除接口")
    @DeleteMapping("/deleteWeather/{id}")
    public Result<?> deleteWeather(@PathVariable("id") String id) {

        return Result.success("操作成功");
    }


    @Operation(summary = "电网概览统计查询接口")
    @GetMapping("/searchPowerGridView")
    public Result<?> searchPowerGridView() {
        return Result.success("操作成功",powerGridViewService.list());
    }

    @Operation(summary = "电网概览统计新增和更新接口")
    @PostMapping("/savOrUpdatePowerGridView")
    public Result<?> savOrUpdatePowerGridView(@RequestBody PowerGridView powerGridView) {
        powerGridViewService.saveOrUpdate(powerGridView);
        return Result.success("操作成功");
    }
    @Operation(summary = "电网概览统计删除接口")
    @DeleteMapping("/deletePowerGridView/{id}")
    public Result<?> deletePowerGridView(@PathVariable("id") String id) {
        powerGridViewService.removeById(id);
        return Result.success("操作成功");
    }

}
