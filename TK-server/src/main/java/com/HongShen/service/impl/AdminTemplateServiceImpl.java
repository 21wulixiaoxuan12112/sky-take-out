package com.HongShen.service.impl;

import com.HongShen.dto.usertemplate.UserTemplatePageQueryDTO;
import com.HongShen.entity.UserTemplate;
import com.HongShen.mapper.AdminTemplateMapper;
import com.HongShen.result.PageResult;
import com.HongShen.service.AdminTemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserTemplate getById(Long id) {
        return adminTemplateMapper.getById(id);
    }

//    @Override
//    public void save(AdminTemplateDTO adminTemplateDTO) throws IOException {
////        创建AdminTemplate
//        AdminTemplate adminTemplate = new AdminTemplate();
////        Document document = Jsoup.parse(adminTemplateDTO.getFilepath());
//        File file = new File(adminTemplateDTO.getFilepath());
//        Path path = Paths.get(adminTemplateDTO.getFilepath());
////        塞入AdminTemplateDTO当中
//        BeanUtils.copyProperties(adminTemplateDTO, adminTemplate);
////
//        String projectRoot = System.getProperty("user.dir");
//        String relativePath = Paths.get(projectRoot).relativize(path).toString();
//        adminTemplate.setFilepath(relativePath);
////
//        adminTemplate.setFilename(file.getName());
//        adminTemplate.setContent(Files.readString(path));
////        adminTemplate.setFilepath(file.getPath());
//        adminTemplate.setCreatetime(LocalDateTime.now());
////       sql语句
//        adminTemplateMapper.save(adminTemplate);
//    }


    @Override
    public PageResult pageQuery(UserTemplatePageQueryDTO userTemplatePageQueryDTO) {
        PageHelper.startPage(userTemplatePageQueryDTO.getPage(),userTemplatePageQueryDTO.getPageSize());
        Page<UserTemplate> page = adminTemplateMapper.pageQuery(userTemplatePageQueryDTO);
        long total = page.getTotal();
        List<UserTemplate> result = page.getResult();
        return new PageResult(total, result);
    }
    @Override
    public void startOrStop(Integer status, Integer id) {
        //update emils set state = ? where id=?
        UserTemplate userTemplate =UserTemplate.builder()
                .status(status)
                .id(id)
                .build();
        adminTemplateMapper.startOrStop(userTemplate);
    }

}
