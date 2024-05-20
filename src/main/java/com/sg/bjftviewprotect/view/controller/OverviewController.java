package com.sg.bjftviewprotect.view.controller;

import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.AreaUserCount;
import com.sg.bjftviewprotect.system.entity.PowerGridView;
import com.sg.bjftviewprotect.system.entity.RegionalIntroduction;
import com.sg.bjftviewprotect.system.service.AreaUserCountService;
import com.sg.bjftviewprotect.system.service.PowerGridViewService;
import com.sg.bjftviewprotect.system.service.RegionalIntroductionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/overview/search")
@Tag(name = "资产概览")
public class OverviewController {
    @Autowired
    private RegionalIntroductionService regionalIntroductionService;
    @Autowired
    private AreaUserCountService areaUserCountService;
    @Autowired
    private PowerGridViewService powerGridViewService;
    @Operation(summary = "北京丰台区域简介查询接口")
    @GetMapping("/regionalIntroduction")
    public Result<?> searchRegionalIntroduction() {
        List<RegionalIntroduction> one = regionalIntroductionService.list();
        return Result.success("查询成功", one);
    }

    @Operation(summary = "区域负荷统计查询接口")
    @GetMapping("/areaLoadCount")
    public Result<?> areaLoadCount() {

        return Result.success("查询成功");
    }

    @Operation(summary = "区域用户统计查询接口")
    @GetMapping("/areaUserCount")
    public Result<?> areaUserCount() {
        List<AreaUserCount> list = areaUserCountService.list();
        return Result.success("查询成功",list);
    }

    @Operation(summary = "区域用电量统计查询接口")
    @GetMapping("/areaElectricityCount")
    public Result<?> areaElectricityCount() {

        return Result.success("查询成功");
    }


    @Operation(summary = "主动运维清单统计查询接口")
    @GetMapping("/operationMaintenance")
    public Result<?> operationMaintenance() {

        return Result.success("查询成功");
    }


    @Operation(summary = "故障告警监测查询接口")
    @GetMapping("/alarmMonitoring")
    public Result<?> alarmMonitoring() {

        return Result.success("查询成功");
    }


    @Operation(summary = "区域天气环境查询接口")
    @GetMapping("/weather")
    public Result<?> weather() {

        return Result.success("查询成功");
    }

    @Operation(summary = "电网概览统计查询接口")
    @GetMapping("/powerGridView")
    public Result<?> powerGridView() {
        List<PowerGridView> list = powerGridViewService.list();
        return Result.success("查询成功", list);
    }


}
