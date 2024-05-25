package com.sg.bjftviewprotect.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.RegionalIntroduction;
import com.sg.bjftviewprotect.system.mapper.RegionalIntroductionMapper;
import com.sg.bjftviewprotect.system.request.RegionalIntroductionRequest;
import com.sg.bjftviewprotect.system.service.RegionalIntroductionService;
import com.sg.bjftviewprotect.system.util.FileUtils;
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
    public int saveOrUpdateRegionalIntroduction(RegionalIntroductionRequest regionalIntroductionRequest) {
        int result;
        String imageToBase64 = FileUtils.fileToBase64(regionalIntroductionRequest.getImages());
        RegionalIntroduction regionalIntroduction = new RegionalIntroduction() {{
            setId(regionalIntroductionRequest.getId());
            setIntroduction(regionalIntroductionRequest.getIntroduction());
            setImages(imageToBase64);
        }};
        if (StringUtils.isBlank(regionalIntroductionRequest.getId())){
            regionalIntroduction.setCreateTime(LocalDateTime.now());
            regionalIntroduction.setIsDelete(CommonConstant.NOT_DELETE);
            regionalIntroductionMapper.delete(new LambdaQueryWrapper<>());
            result = regionalIntroductionMapper.insert(regionalIntroduction);
        } else {
            result = regionalIntroductionMapper.updateById(regionalIntroduction);
        }
        return result;
    }
}
