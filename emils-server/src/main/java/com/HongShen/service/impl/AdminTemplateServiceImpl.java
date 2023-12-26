package com.HongShen.service.impl;

import com.HongShen.dto.admintemplate.AdminTemplateDTO;
import com.HongShen.dto.admintemplate.AdminTemplatePageQueryDTO;
import com.HongShen.entity.AdminTemplate;
import com.HongShen.entity.EmailUser;
import com.HongShen.mapper.AdminTemplateMapper;
import com.HongShen.result.PageResult;
import com.HongShen.service.AdminTemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zy
 * @date 2023/12/26 14:23
 */
@Service
public class AdminTemplateServiceImpl implements AdminTemplateService {
    //    private final String FILE_PATH_EMPTY = "文件路径为空";
    @Autowired
    private AdminTemplateMapper adminTemplateMapper;

    @Override
    public void save(AdminTemplateDTO adminTemplateDTO) throws IOException {
//        创建AdminTemplate
        AdminTemplate adminTemplate = new AdminTemplate();
//        Document document = Jsoup.parse(adminTemplateDTO.getFilepath());
        File file = new File(adminTemplateDTO.getFilepath());
        Path path = Paths.get(adminTemplateDTO.getFilepath());
//        塞入AdminTemplateDTO当中
        BeanUtils.copyProperties(adminTemplateDTO, adminTemplate);
        adminTemplate.setFilename(file.getName());
        adminTemplate.setContent(Files.readString(path));
        adminTemplate.setFilepath(file.getAbsolutePath());
        adminTemplate.setCreatetime(LocalDateTime.now());
//       sql语句
        adminTemplateMapper.save(adminTemplate);
    }

    @Override
    public PageResult pageQuery(AdminTemplatePageQueryDTO adminTemplatePageQueryDTO) {
        PageHelper.startPage(adminTemplatePageQueryDTO.getPage(), adminTemplatePageQueryDTO.getPageSize());
        Page<AdminTemplate> page = adminTemplateMapper.pageQuery(adminTemplatePageQueryDTO);
        long total = page.getTotal();
        List<AdminTemplate> result = page.getResult();
        return new PageResult(total, result);
    }
}
