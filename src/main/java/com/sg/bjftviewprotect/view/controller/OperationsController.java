package com.sg.bjftviewprotect.view.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.FaultAlarm;
import com.sg.bjftviewprotect.system.entity.PowerUserInfo;
import com.sg.bjftviewprotect.system.entity.UserCount;
import com.sg.bjftviewprotect.system.service.FaultAlarmService;
import com.sg.bjftviewprotect.system.service.PowerUserService;
import com.sg.bjftviewprotect.system.service.UserCountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @ClassName OperationsController
 * @Author wangshuo
 * @Date 2024/5/30 09:19
 * @Version 1.0
 **/
@RestController
@RequestMapping("/operations/search")
@Tag(name = "智慧运维")
public class OperationsController {
    @Autowired
    private PowerUserService powerUserService;
    @Autowired
    private UserCountService userCountService;
    @Autowired
    private FaultAlarmService faultAlarmService;
    @Operation(summary = "用户统计查询接口")
    @GetMapping("/userCount")
    public Result<List<UserCount>> userCount() {

        Set<String> highSet = new HashSet<>();
        Set<String> lowSet = new HashSet<>();
        Set<String> processedImportantUsers = new HashSet<>();

        int importantUserNum = 0;

        List<PowerUserInfo> powerUserInfos = powerUserService.list();
        for (PowerUserInfo powerUserInfo : powerUserInfos) {
            if (Objects.equals(powerUserInfo.getType(), CommonConstant.HIGH_VOLTAGE)) {
                // 算法不同，高压用户名会重复，根据名称筛选
                highSet.add(powerUserInfo.getUsername());

                // 按重要用户统计然后计算， 重要用户都是高压用户包含一级重要用户和二级重要用户
                if (powerUserInfo.getImportant() != null && powerUserInfo.getImportant().contains(CommonConstant.USER_IMPORTANT)) {
                    // 检查是否已经处理过该用户
                    if (!processedImportantUsers.contains(powerUserInfo.getUsername())) {
                        importantUserNum++;
                        processedImportantUsers.add(powerUserInfo.getUsername());
                    }
                }

            } else if (Objects.equals(powerUserInfo.getType(), CommonConstant.LOW_VOLTAGE)) {
                // 算法不同，低压用户名会重复，但是所属楼栋不同根据地址+名称筛选 不能根据user_code区分
                lowSet.add(powerUserInfo.getAddress() + powerUserInfo.getUsername());
            }

        }
        int allUserNum = highSet.size() + lowSet.size();
        int highUserNum = highSet.size();
        int lowUserNum = lowSet.size();
        List<UserCount> userCounts = userCountService.list(null);
        int finalImportantUserNum = importantUserNum;
        userCounts.forEach(userCount -> {
            if (("级"+userCount.getName()).equals(CommonConstant.USER_IMPORTANT)) {
                userCount.setNum(finalImportantUserNum);
            } else if (userCount.getName().contains(CommonConstant.USER_NOT_IMPORTANT)) {
                userCount.setNum(allUserNum - finalImportantUserNum);
            } else if (userCount.getName().contains(CommonConstant.USER_HIGH_VOLTAGE)) {
                userCount.setNum(highUserNum);
            } else if (userCount.getName().contains(CommonConstant.USER_LOW_VOLTAGE)) {
                userCount.setNum(lowUserNum);
            }
        });
        return Result.success("查询成功", userCounts);
    }

    @Operation(summary = "用户重要性等级信息")
    @GetMapping("/userType")
    public Result<List<PowerUserInfo>> userType(@RequestParam("typeName") String typeName) {
        LambdaQueryWrapper<PowerUserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        switch (typeName) {
            case "非重要用户":
                lambdaQueryWrapper.like(PowerUserInfo::getImportant, CommonConstant.USER_NOT_IMPORTANT);
                break;
            case "市场化零售客户":
            case "非市场客户":
            case "普通代理购电":
                lambdaQueryWrapper.eq(PowerUserInfo::getMarket, typeName);
                break;
            default:
                lambdaQueryWrapper.like(PowerUserInfo::getImportant, CommonConstant.USER_IMPORTANT);
                break;

        }

        List<PowerUserInfo> powerUserInfos = powerUserService.list(lambdaQueryWrapper);
        return Result.success("查询成功", powerUserInfos);
    }


    @Operation(summary = "开关站告警监测查询")
    @GetMapping("/faultAlarm")
    public Result<List<FaultAlarm>> faultAlarm(@RequestParam(value = "station", required = false) String station) {
        List<FaultAlarm> list = faultAlarmService.list(new LambdaQueryWrapper<FaultAlarm>()
                .like(StringUtils.isNotBlank(station),FaultAlarm::getStation,station)
                .orderByDesc(FaultAlarm::getStatisticTime)
                .select(FaultAlarm::getId, FaultAlarm::getStation, FaultAlarm::getLedger, FaultAlarm::getPointName,
                        FaultAlarm::getPatrol, FaultAlarm::getAlarmContent, FaultAlarm::getStatisticTime)
        );
        return Result.success("查询成功", list);
    }


}
