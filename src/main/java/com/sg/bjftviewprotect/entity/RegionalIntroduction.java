package com.sg.bjftviewprotect.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@TableName("t_regional_introduction")
@ApiModel(value = "RegionalIntroduction对象", description = "")
public class RegionalIntroduction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    @ApiModelProperty("简介")
    @TableField("introduction")
    private String introduction;

    @ApiModelProperty("图片")
    @TableField("images")
    private String images;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("是否删除 1已删除 0未删除")
    @TableField("is_delete")
    @TableLogic
    private Integer isDelete;
}
