package com.sg.bjftviewprotect.system.request;

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
 * @since 2024/05/24 10:47:39
 */
@Getter
@Setter
@Schema(name = "AreaLoadCount", description = "$!{table.comment}")
public class AreaLoadCountRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private String id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "统计时间 yyyy-MM-01")
    private String statisticTime;

    private Integer pageSize;
    private Integer pageNum;
}
