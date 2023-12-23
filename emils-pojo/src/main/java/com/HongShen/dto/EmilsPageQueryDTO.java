package com.HongShen.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/20 17:27
 */
@Data
public class EmilsPageQueryDTO implements Serializable {
    //    邮箱名
    private String mailUser;
    //主键
    private Integer id;

//    private LocalDateTime createTime;
//
//    private LocalDateTime updateTime;
    //页码
    private Integer page;

    //每页显示记录数
    private Integer pageSize;


}
