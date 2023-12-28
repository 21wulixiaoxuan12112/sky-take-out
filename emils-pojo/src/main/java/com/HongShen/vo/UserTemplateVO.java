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
public class UserTemplateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件名")
    private String filename;

    @ApiModelProperty(value = "别名")
    private String alias;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "文件路径")
    private String filepath;

//    @ApiModelProperty(value = "模板内容")
//    private String content;

    @ApiModelProperty(value = "用户id")
    private Long userid;

    @ApiModelProperty(value = "模板状态")
    private Integer status;
}