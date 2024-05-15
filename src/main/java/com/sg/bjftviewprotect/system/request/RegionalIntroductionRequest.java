package com.sg.bjftviewprotect.system.request;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "图片")
    private MultipartFile images;

}
