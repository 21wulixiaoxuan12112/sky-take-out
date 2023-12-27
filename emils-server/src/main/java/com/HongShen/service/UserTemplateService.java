package com.HongShen.service;

import com.HongShen.dto.usertemplate.UserTemplateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zy
 * @date 2023/12/26 20:08
 */
public interface UserTemplateService {

    void set(MultipartFile file) throws IOException;

    void save(UserTemplateDTO userTemplateDTO);
}
