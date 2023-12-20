package com.sky.service.impl;

import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmilsDTO;
import com.sky.entity.Emils;
import com.sky.mapper.EmilsMapper;
import com.sky.service.EmilsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/20 15:28
 */
@Service
public class EmilsServiceImpl implements EmilsService {

    @Autowired
    private EmilsMapper emilsMapper;

    @Override
    public void save(EmilsDTO emilsDTO) {
        Emils emils = new Emils();
        BeanUtils.copyProperties(emilsDTO, emils);
        emils.setState(StatusConstant.DISABLE);
        emils.setMailPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        emils.setCreateTime(LocalDateTime.now());
        emilsMapper.insert(emils);

    }
}
