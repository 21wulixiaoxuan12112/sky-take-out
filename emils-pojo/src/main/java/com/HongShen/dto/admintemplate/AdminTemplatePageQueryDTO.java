package com.HongShen.dto.admintemplate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zy
 * @date 2023/12/26 13:24
 */
@Data
public class AdminTemplatePageQueryDTO implements Serializable {
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件名")
    private String filename;

    @ApiModelProperty(value = "别名")
    private Integer alias;

    @ApiModelProperty(value = "模板内容")
    private String content;
    //页码
    @ApiModelProperty(value = "页码")
    private Integer page;

    //每页显示记录数
    @ApiModelProperty(value = "每页显示记录数")
    private Integer pageSize;
}
