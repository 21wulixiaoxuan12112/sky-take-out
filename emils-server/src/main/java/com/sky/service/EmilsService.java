package com.sky.service;

import com.sky.dto.EmilsDTO;
import com.sky.dto.EmilsPageQueryDTO;
import com.sky.entity.Emils;
import com.sky.result.PageResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @date 2023/12/20 15:19
 */


public interface EmilsService{
//    新增
    void save(EmilsDTO emilsDTO);
//    分页
    PageResult pageQuery(EmilsPageQueryDTO emilsPageQueryDTO);
//    根据id修改邮箱状态
    void startOrStop(Integer state, Integer id);
//    反显
    Emils getById(Long id);
}
