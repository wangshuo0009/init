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
 * @since 2024/05/30 09:24:14
 */
@Getter
@Setter
@TableName("t_user_count")
@Schema(name = "UserCount", description = "$!{table.comment}")
public class UserCount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableField("id")
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "数量")
    @TableField("num")
    private Integer num;
}
