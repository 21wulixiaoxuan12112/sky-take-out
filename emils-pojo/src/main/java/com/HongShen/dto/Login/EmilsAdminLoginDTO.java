package com.HongShen.dto.Login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zy
 * @date 2023/12/23 17:15
 */
@Data
@ApiModel(description = "管理员登录时传递的数据模型")
public class EmilsAdminLoginDTO implements Serializable {
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
