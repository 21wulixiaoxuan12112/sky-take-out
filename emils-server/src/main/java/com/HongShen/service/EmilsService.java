package com.HongShen.service;

import com.HongShen.dto.EmilsDTO;
import com.HongShen.dto.EmilsPageQueryDTO;
import com.HongShen.entity.Emils;
import com.HongShen.result.PageResult;

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

    void deleteById(Long id);

    void update(EmilsDTO emilsDTO);

}
