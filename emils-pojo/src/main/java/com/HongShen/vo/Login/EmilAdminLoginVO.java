package com.HongShen.vo.Login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zy
 * @date 2023/12/23 17:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "管理员登录返回的数据格式")
public class EmilAdminLoginVO implements Serializable {
    @ApiModelProperty("主键值")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;

//    @ApiModelProperty("姓名")
//    private String name;

    @ApiModelProperty("jwt令牌")
    private String token;
}
