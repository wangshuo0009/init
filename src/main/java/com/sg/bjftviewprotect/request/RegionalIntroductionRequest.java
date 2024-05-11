package com.sg.bjftviewprotect.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName RegionalIntroductionRequest
 * @Author wangshuo
 * @Date 2024/5/9 09:58
 * @Version 1.0
 **/
@Data
public class RegionalIntroductionRequest {

    @ApiModelProperty("简介")
    private String introduction;

    @ApiModelProperty("图片")
    private MultipartFile images;

}
