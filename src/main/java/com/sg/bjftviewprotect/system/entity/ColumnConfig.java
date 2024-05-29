package com.sg.bjftviewprotect.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2024/05/29 14:16:02
 */
@Getter
@Setter
@TableName("t_column_config")
@Schema(name = "ColumnConfig", description = "$!{table.comment}")
public class ColumnConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId("id")
    private String id;

    @Schema(description = "指标名称")
    @TableField("title")
    private String title;

    @Schema(description = "字段名")
    @TableField("prop")
    private String prop;

    @Schema(description = "指标id")
    @TableField("module_id")
    private String moduleId;

    @Schema(description = "排序")
    @TableField("sort_no")
    private Integer sortNo;

    @Schema(description = "表名")
    @TableField("table_name")
    private String tableName;

    @Schema(description = "新增和更新接口")
    @TableField("save_or_update_api")
    private String saveOrUpdateApi;

    @Schema(description = "删除接口")
    @TableField("delete_api")
    private String deleteApi;

    @Schema(description = "查询接口")
    @TableField("search_api")
    private String searchApi;
}
