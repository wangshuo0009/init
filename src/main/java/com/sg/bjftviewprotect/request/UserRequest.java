package com.sg.bjftviewprotect.request;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
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
public class UserRequest {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("帐号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("状态1禁用0启用")
    private Integer status;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("角色id")
    private List<String> roleId;
}
