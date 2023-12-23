package com.HongShen.service;

import com.HongShen.dto.EmilsUserDTO;
import com.HongShen.dto.EmilsUserLoginDTO;
import com.HongShen.dto.EmilsUserPageQueryDTO;
import com.HongShen.entity.EmailUser;
import com.HongShen.entity.Emils;
import com.HongShen.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author author
 * @since 2023-12-21
 */
public interface EmailUserService {

    void save(EmilsUserDTO emilsUserDTO);

    PageResult pageQuery(EmilsUserPageQueryDTO emilsUserPageQueryDTO);

    EmailUser getById(Long id);

    void deleteById(Long id);

    void update(EmilsUserDTO emilsUserDTO);

    void startOrStop(Integer status, Integer id);

   EmailUser login(EmilsUserLoginDTO emilsUserLoginDTO);
}
