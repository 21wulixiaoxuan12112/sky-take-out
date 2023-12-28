package com.HongShen.service;

import com.HongShen.dto.usertemplate.UserTemplatePageQueryDTO;
import com.HongShen.entity.UserTemplate;
import com.HongShen.result.PageResult;
import com.HongShen.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zy
 * @date 2023/12/26 20:08
 */
public interface UserTemplateService {

    Result set(MultipartFile file, String alias) throws IOException;


    PageResult pageQuery(UserTemplatePageQueryDTO userTemplatePageQueryDTO);

    void update(Integer id,MultipartFile file,String alias) throws IOException;

    UserTemplate getById(Integer id);

    void deleteById(Long id);
}