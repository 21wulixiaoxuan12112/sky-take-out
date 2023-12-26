package com.HongShen.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/26 13:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTemplateVO  implements Serializable {
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
