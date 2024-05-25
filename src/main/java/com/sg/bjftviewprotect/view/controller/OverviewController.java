package com.sg.bjftviewprotect.view.controller;

import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.AreaUserCount;
import com.sg.bjftviewprotect.system.entity.PowerGridView;
import com.sg.bjftviewprotect.system.entity.PowerUserInfo;
import com.sg.bjftviewprotect.system.entity.RegionalIntroduction;
import com.sg.bjftviewprotect.system.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @Operation(summary = "北京丰台区域简介查询接口")
    @GetMapping("/regionalIntroduction")
    public Result<?> searchRegionalIntroduction() {
        List<RegionalIntroduction> one = regionalIntroductionService.list();
        return Result.success("查询成功", one);
    }

    @Operation(summary = "区域负荷统计查询接口")
    @GetMapping("/areaLoadCount")
    public Result<?> areaLoadCount() {
        //areaLoadCountService.searchAreaLoadCount()
        return Result.success("查询成功");
    }

    @Operation(summary = "区域用户统计查询接口")
    @GetMapping("/areaUserCount")
    public Result<?> areaUserCount() {
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
        double allUserNum = highSet.size() + lowSet.size();
        double highUserNum = highSet.size();
        double lowUserNum = lowSet.size();
        List<AreaUserCount> areaUserCounts = areaUserCountService.list();
        areaUserCounts.forEach(areaUserCount -> {
            if (areaUserCount.getName().contains("总用户")) {
                areaUserCount.setNum((int) allUserNum);
                areaUserCount.setRate(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP));
            } else if (areaUserCount.getName().contains("高压用户")) {
                areaUserCount.setNum((int) highUserNum);
                areaUserCount.setRate(BigDecimal.valueOf((highUserNum / allUserNum) * 100).setScale(2, RoundingMode.HALF_UP));
            } else if (areaUserCount.getName().contains("低压用户")) {
                areaUserCount.setNum((int) lowUserNum);
                areaUserCount.setRate(BigDecimal.valueOf((lowUserNum / allUserNum) * 100).setScale(2, RoundingMode.HALF_UP));
            }
        });
        return Result.success("查询成功", areaUserCounts);
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
