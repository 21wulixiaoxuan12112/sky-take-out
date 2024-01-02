package com.HongShen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理端邮箱表
 * </p>
 *
 * @author author
 * @since 2023-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("email")
@ApiModel(value="Email对象", description="管理端邮箱表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emils implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "邮箱")
    private String mailUser;

    @ApiModelProperty(value = "密码")
    private String mailPassword;

    @ApiModelProperty(value = "状态")
    private Integer state;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @ApiModelProperty(value = "端口号")
    private String portUmber;

    @ApiModelProperty(value = "SMTP服务器地址")
    private String smtpServeraddress;


}
