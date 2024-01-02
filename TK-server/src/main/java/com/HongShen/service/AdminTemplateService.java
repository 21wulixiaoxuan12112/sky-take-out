package com.HongShen.service;

import com.HongShen.dto.admintemplate.AdminTemplateDTO;
import com.HongShen.dto.admintemplate.AdminTemplatePageQueryDTO;
import com.HongShen.dto.usertemplate.UserTemplatePageQueryDTO;
import com.HongShen.entity.UserTemplate;
import com.HongShen.result.PageResult;


/**
 * @author zy
 * @date 2023/12/26 14:22
 */
public interface AdminTemplateService {

//    void save(AdminTemplateDTO adminTemplateDTO) throws IOException;

    PageResult pageQuery(UserTemplatePageQueryDTO userTemplatePageQueryDTO);


    UserTemplate getById(Long id);

    void startOrStop(Integer status, Integer id);
}
