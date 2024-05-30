package com.sg.bjftviewprotect.system.controllerDate;

import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.request.PowerUserInfoRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OperationsDataController
 * @Author wangshuo
 * @Date 2024/5/30 08:32
 * @Version 1.0
 **/
@RestController
@RequestMapping("/operationsData")
@Tag(name = "智慧运维")
@Log4j2
public class OperationsDataController {
    @Autowired
    CommonDataController commonDataController;

    @PostMapping("/user")
    public Result<?> user(PowerUserInfoRequest powerUserInfoRequest){

        return commonDataController.searchPowerUserInfo(powerUserInfoRequest);
    }
}
