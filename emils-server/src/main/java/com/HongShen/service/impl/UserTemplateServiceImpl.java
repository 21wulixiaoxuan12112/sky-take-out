package com.HongShen.service.impl;

import com.HongShen.dto.usertemplate.UserTemplateDTO;
import com.HongShen.entity.AdminTemplate;
import com.HongShen.entity.UserTemplate;
import com.HongShen.mapper.AdminTemplateMapper;
import com.HongShen.mapper.UserTemplateMapper;
import com.HongShen.service.UserTemplateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
    @Autowired
    private UserTemplateMapper userTemplateMapper;


    @Override
    public void save(UserTemplateDTO userTemplateDTO) {
        UserTemplate userTemplate = new UserTemplate();
        set(MultipartFile file);
        userTemplate.setFilename()
    }

    @Override
    public void set(MultipartFile file) throws IOException {
//        文件名字
        String fileName = file.getOriginalFilename();
//        文件路径
        String filePath = "emils-server/src/main/resources/template/" + fileName;
//        文件内容
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);

// 存储文件名称和路径到数据库
//        yourDatabase.storeFileNameAndPath(fileName, filePath);

        // 保存文件到磁盘
//        yourFileStorage.saveFile(file, filePath);
    }
}
