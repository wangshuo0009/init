package com.sg.bjftviewprotect.view.controller;

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
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 09:30:21
 */
@RestController
@RequestMapping("/electricNetworkOverview")
@Tag(name = "电网概览")
public class ElectricNetworkOverviewController {
    @Autowired
    private RegionalIntroductionService regionalIntroductionService;

    @Operation(summary = "查询接口",tags = "北京丰台区域简介")
    @GetMapping("/searchRegionalIntroduction")
    public Result<?> searchRegionalIntroduction(){
        List<RegionalIntroduction> one = regionalIntroductionService.list();
        return Result.success("查询成功",one);
    }

    @Operation(summary = "新增和更新接口",tags = "北京丰台区域简介",description = "新增和更新接口，逻辑删除，会保留历史")
    @PostMapping("/insertOrUpdateRegionalIntroduction")
    @Transactional(rollbackFor = Exception.class)
    public Result<?> insertOrUpdateRegionalIntroduction(RegionalIntroductionRequest regionalIntroductionRequest){
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




}
