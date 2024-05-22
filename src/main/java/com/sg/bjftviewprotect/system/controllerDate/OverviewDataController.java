package com.sg.bjftviewprotect.system.controllerDate;

import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.AreaUserCount;
import com.sg.bjftviewprotect.system.entity.PowerGridView;
import com.sg.bjftviewprotect.system.request.RegionalIntroductionRequest;
import com.sg.bjftviewprotect.system.service.AreaUserCountService;
import com.sg.bjftviewprotect.system.service.PowerGridViewService;
import com.sg.bjftviewprotect.system.service.PowerUserService;
import com.sg.bjftviewprotect.system.service.RegionalIntroductionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 09:30:21
 */
@RestController
@RequestMapping("/overviewData/saveOrUpdate")
@Tag(name = "资产概览")
public class OverviewDataController {
    private static final Logger log = LoggerFactory.getLogger(OverviewDataController.class);
    @Autowired
    private RegionalIntroductionService regionalIntroductionService;
    @Autowired
    private AreaUserCountService areaUserCountService;
    @Autowired
    private PowerGridViewService powerGridViewService;

    @Operation(summary = "北京丰台区域简介新增和更新接口", description = "新增和更新接口，逻辑删除，会保留历史")
    @PostMapping("/regionalIntroduction")
    @Transactional(rollbackFor = Exception.class)
    public Result<?> insertOrUpdateRegionalIntroduction(RegionalIntroductionRequest regionalIntroductionRequest) {
        return regionalIntroductionService.saveOrUpdateRegionalIntroduction(regionalIntroductionRequest);
    }


    @Operation(summary = "区域负荷统计新增和更新接口")
    @PostMapping("/areaLoadCount")
    public Result<?> areaLoadCount() {
        return Result.success("操作成功");
    }

    @Operation(summary = "区域用户统计新增和更新接口")
    @PostMapping("/areaUserCount")
    public Result<?> areaUserCount(@RequestBody AreaUserCount areaUserCount) {
        areaUserCountService.saveOrUpdate(areaUserCount);
        return Result.success("操作成功");
    }

    @Operation(summary = "区域用电量统计新增和更新接口")
    @PostMapping("/areaElectricityCount")
    public Result<?> areaElectricityCount() {

        return Result.success("操作成功");
    }


    @Operation(summary = "主动运维清单统计新增和更新接口")
    @PostMapping("/operationMaintenance")
    public Result<?> operationMaintenance() {

        return Result.success("操作成功");
    }


    @Operation(summary = "故障告警监测新增和更新接口")
    @PostMapping("/alarmMonitoring")
    public Result<?> alarmMonitoring() {

        return Result.success("操作成功");
    }


    @Operation(summary = "区域天气环境新增和更新接口")
    @PostMapping("/weather")
    public Result<?> weather() {

        return Result.success("操作成功");
    }

    @Operation(summary = "电网概览统计新增和更新接口")
    @PostMapping("/powerGridView")
    public Result<?> powerGridView(@RequestBody PowerGridView powerGridView) {
        powerGridViewService.saveOrUpdate(powerGridView);
        return Result.success("操作成功");
    }


}
