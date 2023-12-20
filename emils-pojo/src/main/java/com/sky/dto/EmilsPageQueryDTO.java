package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zy
 * @date 2023/12/20 17:27
 */
@Data
public class EmilsPageQueryDTO implements Serializable {
    //    邮箱名
    private String mailUser;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;


}
