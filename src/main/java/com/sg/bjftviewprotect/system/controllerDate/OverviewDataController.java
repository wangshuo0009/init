package com.sg.bjftviewprotect.system.controllerDate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.RegionalIntroduction;
import com.sg.bjftviewprotect.system.request.RegionalIntroductionRequest;
import com.sg.bjftviewprotect.system.service.RegionalIntroductionService;
import com.sg.bjftviewprotect.system.util.ImagesUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/overviewData/saveOrUpdate")
@Tag(name = "资产概览")
public class OverviewDataController {
    @Autowired
    private RegionalIntroductionService regionalIntroductionService;

    @Operation(summary = "北京丰台区域简介新增和更新接口", description = "新增和更新接口，逻辑删除，会保留历史")
    @PostMapping("/regionalIntroduction")
    @Transactional(rollbackFor = Exception.class)
    public Result<?> insertOrUpdateRegionalIntroduction(RegionalIntroductionRequest regionalIntroductionRequest) {
        regionalIntroductionService.remove(new LambdaQueryWrapper<>());

        String imageToBase64 = ImagesUtils.imageToBase64(regionalIntroductionRequest.getImages());
        if (StringUtils.isBlank(imageToBase64)) {
            return Result.fail("插入失败，图片异常");
        }
        regionalIntroductionService.save(new RegionalIntroduction() {{
            setIntroduction(regionalIntroductionRequest.getIntroduction());
            setImages(imageToBase64);
            setCreateTime(LocalDateTime.now());
            setIsDelete(0);
        }});
        return Result.success("处理成功");
    }


    @Operation(summary = "区域负荷统计新增和更新接口")
    @GetMapping("/areaLoadCount")
    public Result<?> areaLoadCount() {

        return Result.success("查询成功");
    }

    @Operation(summary = "区域用户统计新增和更新接口")
    @GetMapping("/areaUserCount")
    public Result<?> areaUserCount() {

        return Result.success("查询成功");
    }

    @Operation(summary = "区域用电量统计新增和更新接口")
    @GetMapping("/areaElectricityCount")
    public Result<?> areaElectricityCount() {

        return Result.success("查询成功");
    }


    @Operation(summary = "主动运维清单统计新增和更新接口")
    @GetMapping("/operationMaintenance")
    public Result<?> operationMaintenance() {

        return Result.success("查询成功");
    }


    @Operation(summary = "故障告警监测新增和更新接口")
    @GetMapping("/alarmMonitoring")
    public Result<?> alarmMonitoring() {

        return Result.success("查询成功");
    }


    @Operation(summary = "区域天气环境新增和更新接口")
    @GetMapping("/weather")
    public Result<?> weather() {

        return Result.success("查询成功");
    }

    @Operation(summary = "电网概览统计新增和更新接口")
    @GetMapping("/powerGridView")
    public Result<?> powerGridView() {

        return Result.success("查询成功");
    }


}
