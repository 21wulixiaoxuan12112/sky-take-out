package com.HongShen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author author
 * @since 2023-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("email_user")
@ApiModel(value="EmailUser对象", description="会员表")
public class EmailUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "登录时间")
    private Long logintime;

    @ApiModelProperty(value = "创建时间")
    private Long createtime;

    @ApiModelProperty(value = "更新时间")
    private Long updatetime;

    @ApiModelProperty(value = "Token")
    private String token;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "验证")
    private String verification;


}
