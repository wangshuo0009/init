package com.sg.bjftviewprotect.system.controllerDate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.FaultAlarm;
import com.sg.bjftviewprotect.system.entity.PowerUserInfo;
import com.sg.bjftviewprotect.system.request.FaultAlarmRequest;
import com.sg.bjftviewprotect.system.request.PowerUserInfoRequest;
import com.sg.bjftviewprotect.system.service.FaultAlarmService;
import com.sg.bjftviewprotect.system.service.PowerUserService;
import com.sg.bjftviewprotect.system.util.EasyExcelUtil;
import com.sg.bjftviewprotect.system.util.PageUtil;
import com.sg.bjftviewprotect.system.util.TimeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName CommonDataController
 * @Author wangshuo
 * @Date 2024/5/22 11:46
 * @Version 1.0
 **/
@RestController
@RequestMapping("/overviewData/common")
@Tag(name = "通用数据管理")
public class CommonDataController {
    @Autowired
    private PowerUserService powerUserService;
    @Autowired
    private FaultAlarmService faultAlarmService;

    @Operation(summary = "用户档案信息查询", tags = "用户档案")
    @PostMapping("/searchPowerUserInfo")
    public Result<Page<PowerUserInfo>> searchPowerUserInfo(@RequestBody PowerUserInfoRequest powerUserInfoRequest) {
        PageUtil.initPage(powerUserInfoRequest);

        Page<PowerUserInfo> page = new Page<>(powerUserInfoRequest.getPageNum(), powerUserInfoRequest.getPageSize());
        LambdaQueryWrapper<PowerUserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(powerUserInfoRequest.getId()), PowerUserInfo::getId, powerUserInfoRequest.getId())
                .like(StringUtils.isNotBlank(powerUserInfoRequest.getUsername()), PowerUserInfo::getUsername, powerUserInfoRequest.getUsername())
                .like(StringUtils.isNotBlank(powerUserInfoRequest.getUserCode()), PowerUserInfo::getUserCode, powerUserInfoRequest.getUserCode())
                .like(StringUtils.isNotBlank(powerUserInfoRequest.getAmmeter()), PowerUserInfo::getAmmeter, powerUserInfoRequest.getAmmeter());

        powerUserService.page(page, lambdaQueryWrapper);
        return Result.success("查询成功", page);
    }


    @Operation(summary = "用户档案信息新增-- excel",
            tags = "用户档案",
            description = "仅支持新增," +
                    "此接口仅支持单个sheet操作，多个有时间后期再改," +
                    "支持多文件上传，文件要录入的的列索引必须保持一直," +
                    "插入的column 每列，必须对应类的属性名，不在类的列可以写column1，column2等等代替，空白列也要写，用','英文逗号拼接 ")
    @PostMapping("/savePowerUserInfoExcel")
    public Result<PowerUserInfo> savePowerUserInfoExcel(List<MultipartFile> file,
                                    String[] columns,
                                    Integer type) {
        if (type == null || columns == null || columns.length == 0 || file == null || file.isEmpty()) {
            return Result.fail("请求参数不完整");
        }
        LocalDateTime createTime = LocalDateTime.now();
        //String[] columns = {"userCode", "code", "userName","paymentName", "powerUsage", "Column6","important", "market", "loadType"};
        List<PowerUserInfo> finalPowerUsers = new ArrayList<>();
        try {
            for (MultipartFile multipartFile:file){
                List<PowerUserInfo> powerUsers = EasyExcelUtil.importFirstSheet(multipartFile, columns, PowerUserInfo.class, 1);
                powerUsers = powerUsers.stream()
                        .filter(user -> StringUtils.isNotBlank(user.getUserCode()) && StringUtils.isNotBlank(user.getUsername()))
                        .peek(user -> {
                            if (Objects.equals(type, CommonConstant.LOW_VOLTAGE)) {
                                user.setImportant("非重要用户");
                                user.setMarket("普通代理购电");
                            }
                            user.setType(type);
                            user.setCreateTime(createTime);
                        })
                        .collect(Collectors.toList());
                finalPowerUsers.addAll(powerUsers);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        powerUserService.saveOrUpdateBatch(finalPowerUsers);
        return Result.success("操作成功：" + finalPowerUsers.size() + "条数据");
    }


    @Operation(summary = "用户档案信息新增或修改", tags = "用户档案")
    @PostMapping("/saveOrUpdatePowerUserInfo")
    public Result<?> saveOrUpdatePowerUserInfo(@RequestBody PowerUserInfoRequest powerUserInfoRequest) {
        PowerUserInfo powerUserInfo = new PowerUserInfo(){{
            setId(powerUserInfoRequest.getId());
            setUserCode(powerUserInfoRequest.getUserCode());
            setCode(powerUserInfoRequest.getCode());
            setUsername(powerUserInfoRequest.getUsername());
            setPaymentName(powerUserInfoRequest.getPaymentName());
            setAddress(powerUserInfoRequest.getAddress());
            setImportant(powerUserInfoRequest.getImportant());
            setMarket(powerUserInfoRequest.getMarket());
            setLoadType(powerUserInfoRequest.getLoadType());
            setType(powerUserInfoRequest.getType());
            setAmmeter(powerUserInfoRequest.getAmmeter());
            setCreateTime(LocalDateTime.now());
        }};
        powerUserService.saveOrUpdate(powerUserInfo);
        return Result.success("操作成功");
    }

    @Operation(summary = "用户档案信息删除", tags = "用户档案")
    @DeleteMapping("/deletePowerUserInfo/{id}")
    public Result<?> deletePowerUserInfo(@PathVariable("id") String id) {
        powerUserService.removeById(id);
        return Result.success("删除成功");
    }

    @Operation(summary = "故障告警监查询接口",tags = "开关站/故障告警档案")
    @PostMapping("/searchFaultAlarm")
    public Result<Page<FaultAlarm>> searchFaultAlarm(@RequestBody FaultAlarmRequest faultAlarmRequest) {
        PageUtil.initPage(faultAlarmRequest);
        String statisticTime = faultAlarmRequest.getStatisticTime().substring(0, 10);
        LambdaQueryWrapper<FaultAlarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(faultAlarmRequest.getId()),FaultAlarm::getId, faultAlarmRequest.getId())
                .like(StringUtils.isNotBlank(faultAlarmRequest.getStation()),FaultAlarm::getStation, faultAlarmRequest.getStation())
                .apply(StringUtils.isNotBlank(faultAlarmRequest.getStatisticTime()),"date_format(statistic_time,'%Y-%m-%d') = date_format(str_to_date('"+statisticTime+"','%Y-%m-%d'),'%Y-%m-%d')")
                .orderByDesc(FaultAlarm::getStatisticTime);
        Page<FaultAlarm> page = new Page<>(faultAlarmRequest.getPageNum(), faultAlarmRequest.getPageSize());
        faultAlarmService.page(page,lambdaQueryWrapper);
        return Result.success("查询成功", page);
    }

    @Operation(summary = "故障告警监测新增和更新接口",tags = "开关站/故障告警档案")
    @PostMapping("/saveOrUpdateFaultAlarm")
    public Result<?> saveOrUpdateFaultAlarm(FaultAlarmRequest faultAlarmRequest) {
        if (StringUtils.isBlank(faultAlarmRequest.getStation())){
            return Result.fail("请求参数不完整");
        }
        LocalDateTime statisticTime = StringUtils.isNotBlank(faultAlarmRequest.getStatisticTime()) ? TimeUtil.parse(faultAlarmRequest.getStatisticTime()) : null;
        // 告警图片
        //MultipartFile file = faultAlarmRequest.getAlarmImage();
        //String alarmImage = file != null ? FileUtils.fileToBase64(file) : null;
        faultAlarmService.saveOrUpdate(new FaultAlarm(){{
            setId(faultAlarmRequest.getId());
            setStation(faultAlarmRequest.getStation());
            setLedger(faultAlarmRequest.getLedger());
            setPointName(faultAlarmRequest.getPointName());
            setPatrol(faultAlarmRequest.getPatrol());
            setStatisticTime(statisticTime);
            setAlarmContent(faultAlarmRequest.getAlarmContent());
            setAlarmImage(null);
        }});
        return Result.success("操作成功");
    }

    @Operation(summary = "故障告警监测删除接口",tags = "开关站/故障告警档案")
    @DeleteMapping("/deleteFaultAlarm/{id}")
    public Result<?> deleteFaultAlarm(@PathVariable("id") String id) {
        faultAlarmService.removeById(id);
        return Result.success("删除成功");
    }




}
