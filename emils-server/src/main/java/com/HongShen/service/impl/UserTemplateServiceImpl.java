package com.HongShen.service.impl;

import com.HongShen.dto.usertemplate.UserTemplateDTO;
import com.HongShen.entity.AdminTemplate;
import com.HongShen.service.UserTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/26 20:09
 */
@Service
public class UserTemplateServiceImpl implements UserTemplateService {

    @Override
    public void save(UserTemplateDTO userTemplateDTO) {
        //        创建AdminTemplate
//        AdminTemplate adminTemplate = new AdminTemplate();
//        File file = new File(adminTemplateDTO.getFilepath());
//        Path path = Paths.get(adminTemplateDTO.getFilepath());
////        塞入AdminTemplateDTO当中
//        BeanUtils.copyProperties(adminTemplateDTO, adminTemplate);
//        adminTemplate.setFilename(file.getName());
//        adminTemplate.setContent(Files.readString(path));
//        adminTemplate.setFilepath(file.getAbsolutePath());
//        adminTemplate.setCreatetime(LocalDateTime.now());
////       sql语句
//        adminTemplateMapper.save(adminTemplate);


//        从管理员数据表当中选择对应的文件新增

    }
}
