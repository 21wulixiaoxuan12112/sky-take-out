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
import org.apache.commons.io.FileUtils;
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
//        当前登录用户id
        userTemplatePageQueryDTO.setUserid(BaseContext.getCurrentId());
//        开启分页
        PageHelper.startPage(userTemplatePageQueryDTO.getPage(), userTemplatePageQueryDTO.getPageSize());
        Page<UserTemplate> page = userTemplateMapper.pageQuery(userTemplatePageQueryDTO);
        long total = page.getTotal();
        List<UserTemplate> result = page.getResult();
        return new PageResult(total, result);
    }
//   复制文件到指定的文件夹
    public void copyFile(MultipartFile file, String destinationPath) throws IOException {
        byte[] fileBytes = file.getBytes();
        FileUtils.writeByteArrayToFile(new File(destinationPath), fileBytes);
    }



    @Override
    public Result set(MultipartFile file, String alias) throws IOException {
//      在指定目录下创建当前登录用户的文件夹
        String path = "emils-server/src/main/resources/template";
        String folderName = BaseContext.getCurrentId().toString();
//       文件路径
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

        String allPath = pathName + "/" + alias + "." + fileExtension;
        copyFile(file, allPath);
//         文件内容
//        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        LocalDateTime dateTime = LocalDateTime.now();
        Long userid = BaseContext.getCurrentId();
//        Long userId = Long.valueOf(1);
//        存储文件名称和路径到数据库
        UserTemplate userTemplate = new UserTemplate();
        userTemplate.setFilename(file.getOriginalFilename());
        userTemplate.setAlias(alias);
        userTemplate.setFilepath(allPath);
        userTemplate.setCreatetime(dateTime);
//        userTemplate.setContent(content);
        userTemplate.setUserid(userid);
        userTemplate.setStatus(0);
        Integer number = userTemplateMapper.select(alias,userid);
        if (number > 0) {
            return Result.error("文件别名重复！");
        }
        userTemplateMapper.save(userTemplate);
        return Result.success("ok");
    }

    @Override
    public void update(Integer id,MultipartFile file,String alias) throws IOException {
//       先把文件获取到上传到服务器的文件夹内
        String path = "emils-server/src/main/resources/template";
        Long currentId = BaseContext.getCurrentId();
        String folderName = currentId.toString();
//       文件路径
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
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
        File newFolder = new File(directory, folderName);
//        boolean created = newFolder.mkdir();
//        if (!created) {
//            System.out.println("当前登录用户文件夹已经创建！");
//        } else {
//            System.out.println("当前登录用户文件夹创建成功！");
//        }
        String allPath = pathName + "/" + alias + "." + fileExtension;
        copyFile(file, allPath);

//        删除数据库原有的那一行数据，再新增一条数据
//        UserTemplate userTemplate = new UserTemplate();
        UserTemplate userTemplate1 = userTemplateMapper.getById(id, currentId);
//       获取别名,判断是否一样
        String alias1 = userTemplate1.getAlias();
        if(alias.equals(alias1)){
            return;
        }

        userTemplateMapper.update(id,alias,currentId,allPath);

    }

    @Override
    public UserTemplate getById(Integer id) {
        Long currentId = BaseContext.getCurrentId();
        return userTemplateMapper.getById(id,currentId);
    }

    @Override
    public void deleteById(Long id) {
        Long currentId = BaseContext.getCurrentId();
        userTemplateMapper.deleteById(id,currentId);
    }
}