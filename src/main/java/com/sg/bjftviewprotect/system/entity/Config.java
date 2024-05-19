package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/17 11:20:58
 */
@Getter
@Setter
@TableName("t_config")
@Schema(name = "Config", description = "$!{table.comment}")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableField("id")
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "编码")
    @TableField("code")
    private String code;


    @Schema(description = "备注")
    @TableField("remark")
    private String remark;
}
