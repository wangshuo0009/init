package com.sg.bjftviewprotect.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName UserRequest
 * @Author wangshuo
 * @Date 2024/5/11 11:28
 * @Version 1.0
 **/
@Data
@Schema(description = "用户管理请求体")
public class UserRequest {
    @Schema(description = "id")
    private String id;

    @Schema(description = "名字")
    private String name;

    @Schema(description = "帐号")
    private String account;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "状态1禁用0启用")
    private Integer isEnable;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "角色id数组")
    private List<String> roleId;

    private Integer pageSize;
    private Integer pageNum;
}
