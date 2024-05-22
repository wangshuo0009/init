package com.sg.bjftviewprotect.system.controllerDate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.constant.CommonConstant;
import com.sg.bjftviewprotect.system.entity.PowerUserInfo;
import com.sg.bjftviewprotect.system.request.PowerUserInfoRequest;
import com.sg.bjftviewprotect.system.service.PowerUserService;
import com.sg.bjftviewprotect.system.util.EasyExcelUtil;
import com.sg.bjftviewprotect.system.util.PageUtil;
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

    @Operation(summary = "用户档案信息新增接口",
            tags = "用户档案",
            description = "仅支持新增\n" +
                    "此接口仅支持单个sheet操作，多个有时间后期再改\n" +
                    "支持多文件上传，文件要录入的的列索引必须保持一直\n" +
                    "插入的column 没列，必须对应类的属性名，不在类的列可以写column1，column2等等代替，空白列也要写，用','英文逗号拼接 ")
    @PostMapping("/savePowerUserInfoExcel")
    public Result<?> savePowerUserInfoExcel(List<MultipartFile> file,
                                    String[] columns,
                                    Integer type) {
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
    @Operation(summary = "查询用户档案信息", tags = "用户档案")
    @PostMapping("/searchPowerUserInfo")
    public Result<?> searchPowerUserInfo(@RequestBody PowerUserInfoRequest powerUserInfoRequest) {
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

    @Operation(summary = "用户档案信息新增或修改", tags = "用户档案")
    @DeleteMapping("/deletePowerUserInfo/{id}")
    public Result<?> deletePowerUserInfo(@PathVariable("id") String id) {
        powerUserService.removeById(id);
        return Result.success("删除成功");
    }








}
