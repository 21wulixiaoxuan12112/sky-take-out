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
 * 管理员表
 * </p>
 *
 * @author author
 * @since 2023-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
@TableName("email_admin")
@ApiModel(value="EmailAdmin对象", description="管理员表")
public class EmailAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "登录时间")
    private LocalDateTime logintime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;
//
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatetime;

    @ApiModelProperty(value = "Session标识")
    private String token;

    @ApiModelProperty(value = "状态")
    private Integer status;



}
