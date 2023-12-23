package com.HongShen.service;

import com.HongShen.dto.Login.EmilsAdminLoginDTO;
import com.HongShen.entity.EmailAdmin;
import com.HongShen.entity.EmailUser;

/**
 * @author zy
 * @date 2023/12/23 17:08
 */
public interface EmailAdminService {
    EmailAdmin login(EmilsAdminLoginDTO emilsAdminLoginDTO);
}
