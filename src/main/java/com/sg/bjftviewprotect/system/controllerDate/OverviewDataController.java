package com.sg.bjftviewprotect.system.controllerDate;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.*;
import com.sg.bjftviewprotect.system.request.AreaElectricityCountRequest;
import com.sg.bjftviewprotect.system.request.AreaLoadCountRequest;
import com.sg.bjftviewprotect.system.request.OperationMaintenanceRequest;
import com.sg.bjftviewprotect.system.request.RegionalIntroductionRequest;
import com.sg.bjftviewprotect.system.service.*;
import com.sg.bjftviewprotect.system.util.PageUtil;
import com.sg.bjftviewprotect.system.util.TimeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @Autowired
    private AreaElectricityCountService areaElectricityCountService;
    @Autowired
    private OperationMaintenanceService operationMaintenanceService;

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
        return Result.success("删除成功");
    }


    @Operation(summary = "区域负荷统计查询接口")
    @PostMapping("/searchAreaLoadCount")
    public Result<?> searchAreaLoadCount(@RequestBody AreaLoadCountRequest areaLoadCountRequest) {
        PageUtil.initPage(areaLoadCountRequest);
        Page<AreaLoadCount> page = areaLoadCountService.searchAreaLoadCount(areaLoadCountRequest);
        return Result.success("查询成功", page);
    }
    @Operation(summary = "区域负荷统计新增和更新接口")
    @PostMapping("/savOrUpdateAreaLoadCount")
    public Result<?> savOrUpdateAreaLoadCount(@RequestBody AreaLoadCountRequest areaLoadCountRequest) {
        if (StringUtils.isBlank(areaLoadCountRequest.getStatisticTime())){
            return Result.fail("统计时间不能为空");
        } else if (areaLoadCountRequest.getPowerLoad() == null) {
            return Result.fail("负荷值不能为空");
        }
        LocalDateTime statisticTime = TimeUtil.parse(areaLoadCountRequest.getStatisticTime().substring(0, 10) + " 00:00:00");
        areaLoadCountService.saveOrUpdate(new AreaLoadCount(){{
            setId(areaLoadCountRequest.getId());
            setName(areaLoadCountRequest.getName());
            setPowerLoad(areaLoadCountRequest.getPowerLoad());
            setStatisticTime(statisticTime);
            setCreateTime(StringUtils.isBlank(areaLoadCountRequest.getId()) ? LocalDateTime.now() : null);
        }});
        return Result.success("操作成功");
    }
    @Operation(summary = "区域负荷统计删除接口")
    @DeleteMapping("/deleteAreaLoadCount/{id}")
    public Result<?> deleteAreaLoadCount(@PathVariable("id") String id) {
        areaLoadCountService.removeById(id);
        return Result.success("删除成功");
    }


    /**
     * 区域用户统计查询接口 ， 用通用数据管理 - 用户档案，新增删除 同样/overviewData/common/searchPowerUserInfo 接口
     * 数据来源用查询用户档案信息接口对应的用户档案信息表
     * 这里先单表操作， 后续再说
     */
    @Operation(summary = "区域用户统计查询接口")
    @GetMapping("/searchAreaUserCount")
    public Result<?> searchAreaUserCount() {
        List<AreaUserCount> list = areaUserCountService.searchAreaUserCount();
        return Result.success("查询成功成功", list);
    }

    @Operation(summary = "区域用户统计新增和更新接口")
    @PostMapping("/savOrUpdateAreaUserCount")
    public Result<?> savOrUpdateAreaUserCount(@RequestBody AreaUserCount areaUserCount) {
        areaUserCountService.saveOrUpdate(areaUserCount);
        return Result.success("操作成功");
    }
    @Operation(summary = "区域用户统计删除接口")
    @DeleteMapping("/deleteAreaUserCount/{id}")
    public Result<?> deleteAreaUserCount(@PathVariable("id") String id) {
        areaUserCountService.removeById(id);
        return Result.success("操作成功");
    }

    @Operation(summary = "区域用电量查询接口")
    @PostMapping("/searchAreaElectricityCount")
    public Result<?> searchAreaElectricityCount(@RequestBody AreaElectricityCountRequest areaElectricityCountRequest) {
        PageUtil.initPage(areaElectricityCountRequest);
        Page<AreaElectricityCount> page = areaElectricityCountService.searchAreaElectricityCount(areaElectricityCountRequest);
        return Result.success("查询成功成功", page);
    }
    @Operation(summary = "区域用电量统计新增和更新接口")
    @PostMapping("/savOrUpdateAreaElectricityCount")
    public Result<?> savOrUpdateAreaElectricityCount(@RequestBody AreaElectricityCountRequest areaElectricityCountRequest) {

        if (StringUtils.isBlank(areaElectricityCountRequest.getStatisticTime())){
            return Result.fail("统计时间不能为空");
        } else if (areaElectricityCountRequest.getElectricityUsage() == null) {
            return Result.fail("负荷值不能为空");
        }

        LocalDateTime statisticTime = TimeUtil.parse(areaElectricityCountRequest.getStatisticTime().substring(0, 10) + " 00:00:00");
        areaElectricityCountService.saveOrUpdate(new AreaElectricityCount(){{
            setId(StringUtils.isBlank(areaElectricityCountRequest.getId()) ? null : areaElectricityCountRequest.getId());
            setName(areaElectricityCountRequest.getName());
            setStatisticTime(statisticTime);
            setElectricityUsage(areaElectricityCountRequest.getElectricityUsage());
            setCreateTime(StringUtils.isBlank(areaElectricityCountRequest.getId()) ? LocalDateTime.now() : null);
        }});
        return Result.success("操作成功");
    }
    @Operation(summary = "区域用电量统计删除接口")
    @DeleteMapping("/deleteAreaElectricityCount/{id}")
    public Result<?> deleteAreaElectricityCount(@PathVariable("id") String id) {
        areaElectricityCountService.removeById(id);
        return Result.success("操作成功");
    }

    @Operation(summary = "主动运维清单统计查询接口")
    @PostMapping("/searchOperationMaintenance")
    public Result<?> searchOperationMaintenance(@RequestBody OperationMaintenanceRequest operationMaintenanceRequest) {
        PageUtil.initPage(operationMaintenanceRequest);
        Page<OperationMaintenance> page = operationMaintenanceService.searchOperationMaintenance(operationMaintenanceRequest);
        return Result.success("操作成功", page);
    }

    @Operation(summary = "主动运维清单统计新增和更新接口")
    @PostMapping("/savOrUpdateOperationMaintenance")
    public Result<?> savOrUpdateOperationMaintenance(@RequestBody OperationMaintenanceRequest operationMaintenanceRequest) {
        if (operationMaintenanceRequest.getCode() == null || operationMaintenanceRequest.getNum() == null || operationMaintenanceRequest.getType() == null ||StringUtils.isBlank(operationMaintenanceRequest.getStatisticTime())){
            return Result.fail("请求参数不完整");
        }
        LocalDateTime statisticTime = TimeUtil.parse(operationMaintenanceRequest.getStatisticTime().substring(0, 10) + " 00:00:00");
        operationMaintenanceService.saveOrUpdate(new OperationMaintenance(){{
            setId(operationMaintenanceRequest.getId());
            setName(operationMaintenanceRequest.getName());
            setStatisticTime(statisticTime);
            setCode(operationMaintenanceRequest.getCode());
            setNum(operationMaintenanceRequest.getNum());
            setType(operationMaintenanceRequest.getType());
        }});
        return Result.success("操作成功");
    }
    @Operation(summary = "主动运维清单统计删除接口")
    @DeleteMapping("/deleteOperationMaintenance/{id}")
    public Result<?> deleteOperationMaintenance(@PathVariable("id") String id) {
        operationMaintenanceService.removeById(id);
        return Result.success("操作成功");
    }

    // TODO 接别人系统
    @Operation(summary = "故障告警监测新增和更新接口", description = "接别人系统")
    @PostMapping("/savOrUpdateAlarmMonitoring")
    public Result<?> savOrUpdateAlarmMonitoring() {

        return Result.success("操作成功");
    }
    // TODO 接别人系统
    @Operation(summary = "故障告警监测删除接口", description = "接别人系统")
    @DeleteMapping("/deleteAlarmMonitoring/{id}")
    public Result<?> deleteAlarmMonitoring(@PathVariable("id") String id) {

        return Result.success("操作成功");
    }
    // TODO 接别人系统
    @Operation(summary = "区域天气环境新增和更新接口", description = "接别人系统")
    @PostMapping("/savOrUpdateWeather")
    public Result<?> savOrUpdateWeather() {

        return Result.success("操作成功");
    }
    // TODO 接别人系统
    @Operation(summary = "区域天气环境删除接口", description = "接别人系统")
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
