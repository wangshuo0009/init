package com.sg.bjftviewprotect.view.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.*;
import com.sg.bjftviewprotect.system.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.sg.bjftviewprotect.system.util.NumberUtil.roundBigDecimal;

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
    @Autowired
    private PowerUserService powerUserService;
    @Autowired
    private AreaLoadCountService areaLoadCountService;
    @Autowired
    private AreaElectricityCountService areaElectricityCountService;
    @Autowired
    private OperationMaintenanceService operationMaintenanceService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private FaultAlarmService faultAlarmService;

    @Operation(summary = "北京丰台区域简介查询接口")
    @GetMapping("/regionalIntroduction")
    public Result<List<RegionalIntroduction>> searchRegionalIntroduction() {
        List<RegionalIntroduction> one = regionalIntroductionService.searchRegionalIntroduction();
        return Result.success("查询成功", one);
    }

    @Operation(summary = "区域负荷统计查询接口")
    @GetMapping("/areaLoadCount")
    public Result<Map<String, List<AreaLoadCount>>> areaLoadCount() {
        Map<String, List<AreaLoadCount>> map = areaLoadCountService.searchAreaLoadCountForView();
        return Result.success("查询成功", map);
    }

    @Operation(summary = "区域用户统计查询接口")
    @GetMapping("/areaUserCount")
    public Result<List<AreaUserCount>> areaUserCount() {

        Set<String> highSet = new HashSet<>();
        Set<String> lowSet = new HashSet<>();
        List<PowerUserInfo> powerUserInfos = powerUserService.list();
        for (PowerUserInfo powerUserInfo : powerUserInfos) {
            if (Objects.equals(powerUserInfo.getType(), CommonConstant.HIGH_VOLTAGE)) {
                // 算法不同，高压用户名会重复，根据名称筛选
                highSet.add(powerUserInfo.getUsername());
            } else if (Objects.equals(powerUserInfo.getType(), CommonConstant.LOW_VOLTAGE)) {
                // 算法不同，低压用户名会重复，但是所属楼栋不同根据地址+名称筛选
                lowSet.add(powerUserInfo.getAddress() + powerUserInfo.getUserCode());
            }
        }
        int allUserNum = highSet.size() + lowSet.size();
        int highUserNum = highSet.size();
        int lowUserNum = lowSet.size();
        List<AreaUserCount> areaUserCounts = areaUserCountService.searchAreaUserCount();
        areaUserCounts.forEach(areaUserCount -> {
            if (areaUserCount.getName().contains("总用户")) {
                areaUserCount.setNum(allUserNum);
                areaUserCount.setRate(roundBigDecimal(100,2).doubleValue());
            } else if (areaUserCount.getName().contains("高压用户")) {
                areaUserCount.setNum(highUserNum);
                double rate = (double) highUserNum / (double) allUserNum * 100;
                areaUserCount.setRate(roundBigDecimal(rate, 2).doubleValue());
            } else if (areaUserCount.getName().contains("低压用户")) {
                areaUserCount.setNum(lowUserNum);
                double rate = (double) lowUserNum / (double) allUserNum * 100;
                areaUserCount.setRate(roundBigDecimal(rate, 2).doubleValue());
            }
        });
        return Result.success("查询成功", areaUserCounts);
    }

    @Operation(summary = "区域用电量统计查询接口")
    @GetMapping("/areaElectricityCount")
    public Result<Map<String, List<AreaElectricityCount>>> areaElectricityCount() {
        Map<String, List<AreaElectricityCount>> map = areaElectricityCountService.searchAreaElectricityCountForView();
        return Result.success("查询成功", map);
    }


    @Operation(summary = "主动运维清单统计查询接口", description = "类型1巡视工单，2故障工单")
    @GetMapping("/operationMaintenance")
    public Result<Map<Integer, OperationMaintenanceUtil>> operationMaintenance(@RequestParam("type") Integer type) {
        Map<Integer, OperationMaintenanceUtil> map = operationMaintenanceService.searchOperationMaintenanceForView(type);
        return Result.success("查询成功", map);
    }


    @Operation(summary = "故障告警监测查询接口")
    @GetMapping("/faultAlarm")
    public Result<List<FaultAlarm>> faultAlarm() {
        List<FaultAlarm> list = faultAlarmService.list(new LambdaQueryWrapper<FaultAlarm>()
                .orderByDesc(FaultAlarm::getStatisticTime)
                .select(FaultAlarm::getId, FaultAlarm::getStation, FaultAlarm::getLedger, FaultAlarm::getPointName,
                        FaultAlarm::getPatrol, FaultAlarm::getAlarmContent, FaultAlarm::getStatisticTime)
        );
        return Result.success("查询成功", list);
    }


    @Operation(summary = "区域天气环境查询接口")
    @GetMapping("/weather")
    public Result<Weather> weather() {
        Weather one = weatherService.getOne(new QueryWrapper<Weather>().apply("date_format(statistic_time,'%Y-%m-%d') = date_format(CURRENT_DATE (),'%Y-%m-%d')"), false);
        return Result.success("查询成功", one);
    }

    @Operation(summary = "电网概览统计查询接口")
    @GetMapping("/powerGridView")
    public Result<List<PowerGridView>> powerGridView() {
        List<PowerGridView> list = powerGridViewService.list();
        return Result.success("查询成功", list);
    }


}
