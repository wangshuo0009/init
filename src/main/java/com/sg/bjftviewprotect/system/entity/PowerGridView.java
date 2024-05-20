package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/20 09:59:57
 */
@Getter
@Setter
@TableName("t_power_grid_view")
@Schema(name = "PowerGridView", description = "$!{table.comment}")
public class PowerGridView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId("id")
    private String id;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "数据")
    @TableField("data")
    private String data;

    @Schema(description = "单位")
    @TableField("unit")
    private String unit;
}
