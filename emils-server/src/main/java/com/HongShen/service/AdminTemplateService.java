package com.HongShen.service;

import com.HongShen.dto.admintemplate.AdminTemplateDTO;
import com.HongShen.dto.admintemplate.AdminTemplatePageQueryDTO;
import com.HongShen.result.PageResult;

import java.io.IOException;

/**
 * @author zy
 * @date 2023/12/26 14:22
 */
public interface AdminTemplateService {

    void save(AdminTemplateDTO adminTemplateDTO) throws IOException;

    //    void save(String filepath) throws IOException;
    PageResult pageQuery(AdminTemplatePageQueryDTO adminTemplatePageQueryDTO);


}
