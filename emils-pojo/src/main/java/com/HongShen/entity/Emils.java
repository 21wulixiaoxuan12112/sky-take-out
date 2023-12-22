package com.HongShen.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/20 11:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Emils implements Serializable {

    private Integer id;

    private String mailUser;

    private String mailPassword;

    private Integer state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
