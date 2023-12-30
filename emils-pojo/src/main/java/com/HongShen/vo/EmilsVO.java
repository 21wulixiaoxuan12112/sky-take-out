package com.HongShen.vo;

/**
 * @author zy
 * @date 2023/12/20 12:34
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "邮箱返回的数据格式")
public class EmilsVO implements Serializable {

    @ApiModelProperty("主键值")
    private Integer id;

    @ApiModelProperty("邮箱名")
    private String mailUser;
//
    @ApiModelProperty("密码")
    private String mailPassword;

    @ApiModelProperty("邮箱状态")
    private Integer state;

    @ApiModelProperty("创建时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "端口号")
    private String portUmber;

    @ApiModelProperty(value = "SMTP服务器地址")
    private String smtpServeraddress;
}
