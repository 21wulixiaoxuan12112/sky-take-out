package com.sky.dto;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/20 10:22
 */
@Data
public class EmilsDTO implements Serializable {
    private Integer id;

    private String mailUser;

    private String mailPassword;

    private Integer state;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
