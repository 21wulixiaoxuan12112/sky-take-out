package com.sky.service;

import com.sky.dto.EmilsDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @date 2023/12/20 15:19
 */


public interface EmilsService{
    void save(EmilsDTO emilsDTO);
}
