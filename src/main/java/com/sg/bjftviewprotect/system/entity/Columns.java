package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName Columns
 * @Author wangshuo
 * @Date 2024/5/29 14:25
 * @Version 1.0
 **/
@Data
public class Columns {
    @Schema(description = "指标名称")
    @TableField("title")
    private String title;

    @Schema(description = "字段名")
    @TableField("prop")
    private String prop;
}
