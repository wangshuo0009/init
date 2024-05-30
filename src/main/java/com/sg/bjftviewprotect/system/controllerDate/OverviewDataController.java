package com.sg.bjftviewprotect.system.controllerDate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.*;
import com.sg.bjftviewprotect.system.request.*;
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
    private PowerUserService powerUserService;
    @Autowired
    private PowerGridViewService powerGridViewService;
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
    @Autowired
    private CommonDataController commonDataController;

    @Operation(summary = "北京丰台区域简介查询接口")
    @PostMapping("/searchRegionalIntroduction")
    public Result<List<RegionalIntroduction>> searchRegionalIntroduction() {
        List<RegionalIntroduction> list = regionalIntroductionService.list();
        return Result.success("查询成功", list);
    }

    @Operation(summary = "北京丰台区域简介新增和更新接口")
    @PostMapping("/saveOrUpdateRegionalIntroduction")
    public Result<?> saveOrUpdateRegionalIntroduction(RegionalIntroductionRequest regionalIntroductionRequest) {
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
    public Result<Page<AreaLoadCount>> searchAreaLoadCount(@RequestBody AreaLoadCountRequest areaLoadCountRequest) {
        PageUtil.initPage(areaLoadCountRequest);
        Page<AreaLoadCount> page = areaLoadCountService.searchAreaLoadCount(areaLoadCountRequest);
        return Result.success("查询成功", page);
    }
    @Operation(summary = "区域负荷统计新增和更新接口")
    @PostMapping("/saveOrUpdateAreaLoadCount")
    public Result<?> saveOrUpdateAreaLoadCount(@RequestBody AreaLoadCountRequest areaLoadCountRequest) {
        if (StringUtils.isBlank(areaLoadCountRequest.getStatisticTime())){
            return Result.fail("统计时间不能为空");
        } else if (areaLoadCountRequest.getPowerLoad() == null) {
            return Result.fail("负荷值不能为空");
        }
        LocalDateTime statisticTime = TimeUtil.parseYYYYMMDD(areaLoadCountRequest.getStatisticTime());
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
    @Operation(summary = "区域用户统计查询接口",description = "用用户档案接口")
    @PostMapping("/searchAreaUserCount")
    public Result<Page<PowerUserInfo>> searchAreaUserCount(@RequestBody PowerUserInfoRequest powerUserInfoRequest) {
        //List<AreaUserCount> list = areaUserCountService.searchAreaUserCount();
        //return Result.success("查询成功成功", list);
        //return Result.fail("弃用了，用用户档案接口");
        return commonDataController.searchPowerUserInfo(powerUserInfoRequest);
    }

    @Operation(summary = "区域用户统计新增和更新接口",description = "用用户档案接口")
    @PostMapping("/saveOrUpdateAreaUserCount")
    public Result<?> saveOrUpdateAreaUserCount(@RequestBody PowerUserInfoRequest powerUserInfoRequest) {
        //areaUserCountService.saveOrUpdate(areaUserCount);
        //return Result.fail("弃用了，用用户档案接口");
        return commonDataController.saveOrUpdatePowerUserInfo(powerUserInfoRequest);

    }
    @Operation(summary = "区域用户统计删除接口",description = "用用户档案接口")
    @DeleteMapping("/deleteAreaUserCount/{id}")
    public Result<?> deleteAreaUserCount(@PathVariable("id") String id) {
        //areaUserCountService.removeById(id);
        //return Result.fail("弃用了，用用户档案接口");
        return commonDataController.deletePowerUserInfo(id);
    }

    @Operation(summary = "区域用电量查询接口")
    @PostMapping("/searchAreaElectricityCount")
    public Result<Page<AreaElectricityCount>> searchAreaElectricityCount(@RequestBody AreaElectricityCountRequest areaElectricityCountRequest) {
        PageUtil.initPage(areaElectricityCountRequest);
        Page<AreaElectricityCount> page = areaElectricityCountService.searchAreaElectricityCount(areaElectricityCountRequest);
        return Result.success("查询成功成功", page);
    }
    @Operation(summary = "区域用电量统计新增和更新接口")
    @PostMapping("/saveOrUpdateAreaElectricityCount")
    public Result<?> saveOrUpdateAreaElectricityCount(@RequestBody AreaElectricityCountRequest areaElectricityCountRequest) {

        if (StringUtils.isBlank(areaElectricityCountRequest.getStatisticTime())){
            return Result.fail("统计时间不能为空");
        } else if (areaElectricityCountRequest.getElectricityUsage() == null) {
            return Result.fail("负荷值不能为空");
        }

        LocalDateTime statisticTime = TimeUtil.parseYYYYMMDD(areaElectricityCountRequest.getStatisticTime());
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
    public Result<Page<OperationMaintenance>> searchOperationMaintenance(@RequestBody OperationMaintenanceRequest operationMaintenanceRequest) {
        PageUtil.initPage(operationMaintenanceRequest);
        Page<OperationMaintenance> page = operationMaintenanceService.searchOperationMaintenance(operationMaintenanceRequest);
        return Result.success("操作成功", page);
    }

    @Operation(summary = "主动运维清单统计新增和更新接口")
    @PostMapping("/saveOrUpdateOperationMaintenance")
    public Result<?> saveOrUpdateOperationMaintenance(@RequestBody OperationMaintenanceRequest operationMaintenanceRequest) {
        if (operationMaintenanceRequest.getCode() == null || operationMaintenanceRequest.getNum() == null || operationMaintenanceRequest.getType() == null ||StringUtils.isBlank(operationMaintenanceRequest.getStatisticTime())){
            return Result.fail("请求参数不完整");
        }
        LocalDateTime statisticTime = TimeUtil.parseYYYYMMDD(operationMaintenanceRequest.getStatisticTime());
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


    @Operation(summary = "故障告警监查询接口",description = "用的是故障告警档案接口")
    @PostMapping("/searchFaultAlarm")
    public Result<Page<FaultAlarm>> searchFaultAlarm(@RequestBody FaultAlarmRequest faultAlarmRequest) {
        return commonDataController.searchFaultAlarm(faultAlarmRequest);
    }

    @Operation(summary = "故障告警监测新增和更新接口",description = "用的是故障告警档案接口")
    @PostMapping("/saveOrUpdateFaultAlarm")
    public Result<?> saveOrUpdateFaultAlarm(FaultAlarmRequest faultAlarmRequest) {
        return commonDataController.saveOrUpdateFaultAlarm(faultAlarmRequest);
    }

    @Operation(summary = "故障告警监测删除接口",description = "用的是故障告警档案接口")
    @DeleteMapping("/deleteFaultAlarm/{id}")
    public Result<?> deleteFaultAlarm(@PathVariable("id") String id) {
        return commonDataController.deleteFaultAlarm(id);
    }


    @Operation(summary = "区域天气环境查询接口")
    @PostMapping("/searchWeather")
    public Result<Page<Weather>> searchWeather(@RequestBody WeatherRequest weatherRequest) {
        PageUtil.initPage(weatherRequest);
        Page<Weather> page = new Page<>(weatherRequest.getPageNum(), weatherRequest.getPageSize());
        LambdaQueryWrapper<Weather> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.apply("date_format(statistic_time,'%Y-%m-%d') >= date_format(CURRENT_DATE (),'%Y-%m-%d')")
                .orderByAsc(Weather::getStatisticTime);
        weatherService.page(page);

        return Result.success("操作成功", page);
    }


    @Operation(summary = "区域天气环境新增和更新接口")
    @PostMapping("/saveOrUpdateWeather")
    public Result<?> saveOrUpdateWeather(@RequestBody WeatherRequest weatherRequest) {
        LocalDateTime statisticTime = TimeUtil.parseYYYYMMDD(weatherRequest.getStatisticTime());
        weatherService.saveOrUpdate(new Weather(){{
            setId(weatherRequest.getId());
            setWeather(weatherRequest.getWeather());
            setHumidity(weatherRequest.getHumidity());
            setAirPressure(weatherRequest.getAirPressure());
            setRainfall(weatherRequest.getRainfall());
            setTemp(weatherRequest.getTemp());
            setWindDirection(weatherRequest.getWindDirection());
            setWindForce(weatherRequest.getWindForce());
            setStatisticTime(statisticTime);
        }});
        return Result.success("操作成功");
    }

    @Operation(summary = "区域天气环境删除接口")
    @DeleteMapping("/deleteWeather/{id}")
    public Result<?> deleteWeather(@PathVariable("id") String id) {
        weatherService.removeById(id);
        return Result.success("删除成功");
    }


    @Operation(summary = "电网概览统计查询接口")
    @PostMapping("/searchPowerGridView")
    public Result<List<PowerGridView>> searchPowerGridView() {
        return Result.success("操作成功",powerGridViewService.list());
    }

    @Operation(summary = "电网概览统计新增和更新接口")
    @PostMapping("/saveOrUpdatePowerGridView")
    public Result<?> saveOrUpdatePowerGridView(@RequestBody PowerGridView powerGridView) {
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
