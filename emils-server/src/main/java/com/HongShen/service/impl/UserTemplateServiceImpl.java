package com.HongShen.service.impl;

import com.HongShen.context.BaseContext;
import com.HongShen.dto.usertemplate.UserTemplatePageQueryDTO;
import com.HongShen.entity.UserTemplate;
import com.HongShen.mapper.UserTemplateMapper;
import com.HongShen.result.PageResult;
import com.HongShen.result.Result;
import com.HongShen.service.UserTemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zy
 * @date 2023/12/26 20:09
 */
@Service
public class UserTemplateServiceImpl implements UserTemplateService {
    @Autowired
    private UserTemplateMapper userTemplateMapper;


    @Override
    public PageResult pageQuery(UserTemplatePageQueryDTO userTemplatePageQueryDTO) {
        //        开启分页
        PageHelper.startPage(userTemplatePageQueryDTO.getPage(), userTemplatePageQueryDTO.getPageSize());
        Page<UserTemplate> page = userTemplateMapper.pageQuery(userTemplatePageQueryDTO);
        long total = page.getTotal();
        List<UserTemplate> result = page.getResult();
        return new PageResult(total, result);
    }


    @Override
    public Result set(MultipartFile file, String alais) throws IOException {
//      在指定目录下创建当前登录用户的文件夹
        String path = "emils-server/src/main/resources/template";
//不对     String folderName = BaseContext.getCurrentId().toString();
        String folderName = "1";
//       文件路径
//        String pathName = path + "/" + folderName;
        String pathName = path + "/" + folderName;
//      获取文件拓展名
        String originalFilename = file.getOriginalFilename(); // 获取原始文件名
        String fileExtension = null;

        if (originalFilename != null) {
//       查找最后一个点的索引位置
            int dotIndex = originalFilename.lastIndexOf(".");
            if (dotIndex >= 0 && dotIndex < originalFilename.length() - 1) {
//       提取扩展名部分
                fileExtension = originalFilename.substring(dotIndex + 1);
            }
        }

        File directory = new File(path);
        // 检查目录是否存在，如果不存在则创建
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File newFolder = new File(directory, folderName);
        boolean created = newFolder.mkdir();
        if (!created) {
            System.out.println("当前登录用户文件夹已经创建！");
        } else {
            System.out.println("当前登录用户文件夹创建成功！");
        }

        String allPath = pathName + "/" + alais + "." + fileExtension;
//        将文件copy到指定目录下
        file.transferTo(new File(allPath));
//         文件内容
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        LocalDateTime dateTime = LocalDateTime.now();
//不对        Long userId = BaseContext.getCurrentId();
        Long userId = Long.valueOf(1);
//        存储文件名称和路径到数据库
        UserTemplate userTemplate = new UserTemplate();
        userTemplate.setFilename(alais);
        userTemplate.setFilepath(allPath);
        userTemplate.setCreatetime(dateTime);
        userTemplate.setContent(content);
        userTemplate.setUserId(userId);
        Integer number = userTemplateMapper.select(userTemplate.getFilename());
        if (number > 0) {
            return Result.error("文件别名重复！");
        }
        userTemplateMapper.save(userTemplate);
        return Result.success("ok");
    }

    @Override
    public void update(MultipartFile file) {
        String fileName = file.getOriginalFilename();

    }
}