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
 * 邮件发送记录表
 * </p>
 *
 * @author author
 * @since 2023-12-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("send_record")
@ApiModel(value="SendRecord对象", description="邮件发送记录表")
public class SendRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "接收方邮箱")
    private String receiveemail;

    @ApiModelProperty(value = "发送方邮箱")
    private String sendemail;

    @ApiModelProperty(value = "发送模板别名")
    private String alias;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendtime;

    @ApiModelProperty(value = "发送状态：0为发送失败，1为发送成功")
    private String status;

    @ApiModelProperty(value = "发送方用户")
    private String username;


}
