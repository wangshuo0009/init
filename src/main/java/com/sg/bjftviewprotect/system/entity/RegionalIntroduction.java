package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/09 09:30:21
 */
@Data
@TableName("t_regional_introduction")
@Schema(name = "RegionalIntroduction", description = "$!{table.comment}")
public class RegionalIntroduction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    @Schema(description = "简介")
    @TableField("introduction")
    private String introduction;

    @Schema(description = "图片")
    @TableField("images")
    private String images;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "是否删除 1已删除 0未删除")
    @TableField("is_delete")
    private Integer isDelete;
}
