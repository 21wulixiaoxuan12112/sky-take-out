package com.HongShen.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/22 17:01
 */
@Data
public class EmilsUserPageQueryDTO implements Serializable {
    @ApiModelProperty("主键值")
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;


    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

//    @ApiModelProperty(value = "登录时间")
//    private LocalDateTime loginTime;

    //页码
    private Integer page;

    //每页显示记录数
    private Integer pageSize;
}
