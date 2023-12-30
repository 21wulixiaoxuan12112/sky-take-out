package com.HongShen.dto.email;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/20 10:22
 */
@Data
@ApiModel
public class EmilsDTO implements Serializable {
    private Integer id;

    private String mailUser;

    private String mailPassword;

    private Integer state;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @ApiModelProperty(value = "端口号")
    private String portUmber;

    @ApiModelProperty(value = "SMTP服务器地址")
    private String smtpServeraddress;
}
