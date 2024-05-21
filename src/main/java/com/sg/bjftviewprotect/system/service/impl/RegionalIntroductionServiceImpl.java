package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.RegionalIntroduction;
import com.sg.bjftviewprotect.system.mapper.RegionalIntroductionMapper;
import com.sg.bjftviewprotect.system.request.RegionalIntroductionRequest;
import com.sg.bjftviewprotect.system.service.RegionalIntroductionService;
import com.sg.bjftviewprotect.system.util.ImagesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 09:30:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RegionalIntroductionServiceImpl extends ServiceImpl<RegionalIntroductionMapper, RegionalIntroduction> implements RegionalIntroductionService {

    @Autowired
    private RegionalIntroductionMapper regionalIntroductionMapper;
    @Override
    public Result<?> saveOrUpdateRegionalIntroduction(RegionalIntroductionRequest regionalIntroductionRequest) {
        String imageToBase64 = ImagesUtils.imageToBase64(regionalIntroductionRequest.getImages());
        if (StringUtils.isBlank(imageToBase64)) {
            return Result.fail("插入失败，图片异常");
        }
        RegionalIntroduction regionalIntroduction = new RegionalIntroduction() {{
            setId(regionalIntroductionRequest.getId());
            setIntroduction(regionalIntroductionRequest.getIntroduction());
            setImages(imageToBase64);
        }};
        if (StringUtils.isBlank(regionalIntroductionRequest.getId())){
            regionalIntroduction.setCreateTime(LocalDateTime.now());
            regionalIntroduction.setIsDelete(0);
            regionalIntroductionMapper.delete(new LambdaQueryWrapper<>());
            regionalIntroductionMapper.insert(regionalIntroduction);
        } else {
            regionalIntroductionMapper.updateById(regionalIntroduction);
        }
        return Result.success("操作成功");
    }
}
