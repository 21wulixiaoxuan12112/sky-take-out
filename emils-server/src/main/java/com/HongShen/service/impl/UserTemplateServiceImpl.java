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
    public void save(MultipartFile file) {
//        文件名字
        String fileName = file.getOriginalFilename();
//        文件路径
        String filePath = "emils-server/src/main/resources/template/" + fileName;
//        文件内容
        Paths.get(file.)
        String content =
//         创建时间
// 存储文件名称和路径到数据库
                yourDatabase.storeFileNameAndPath(fileName, filePath);

        // 保存文件到磁盘
        yourFileStorage.saveFile(file, filePath);
    }
}
