package com.HongShen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员模板
 * </p>
 *
 * @author author
 * @since 2023-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("admin_template")
@ApiModel(value="AdminTemplate对象", description="管理员模板")
public class AdminTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件名")
    private String filename;

    @ApiModelProperty(value = "别名")
    private Integer alias;

    @ApiModelProperty(value = "文件路径")
    private String filepath;

    @ApiModelProperty(value = "模板内容")
    private String content;

    @ApiModelProperty(value = "模板创建时间 ")
    private LocalDateTime createtime;

//    @ApiModelProperty(value = "逻辑删除")
//    private  Integer deleted;
}
