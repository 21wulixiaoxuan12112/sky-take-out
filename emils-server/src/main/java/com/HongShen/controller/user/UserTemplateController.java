package com.HongShen.controller.user;

import com.HongShen.dto.admintemplate.AdminTemplateDTO;
import com.HongShen.dto.usertemplate.UserTemplateDTO;
import com.HongShen.result.Result;
import com.HongShen.service.AdminTemplateService;
import com.HongShen.service.UserTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author zy
 * @date 2023/12/26 20:06
 */
@RestController
@RequestMapping("/user/emils/template")
@Slf4j
@Api(tags = "用户模板管理相关接口")
public class UserTemplateController {
    @Autowired
    private UserTemplateService userTemplateService;


    @PostMapping
    @ApiOperation("新增模板")
    public Result saveUserTemplate(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("新增模板：{}", file);
     // 在这里处理文件，例如保存到磁盘或执行相应的业务逻辑
        if (!file.isEmpty()) {
//            String fileName = file.getOriginalFilename();
//            String filePath = "emils-server/src/main/resources/template/" + fileName;
//            // 存储文件名称和路径到数据库
//            yourDatabase.storeFileNameAndPath(fileName, filePath);
//
//            // 保存文件到磁盘
//            yourFileStorage.saveFile(file, filePath);
            userTemplateService.save(file);
            return Result.success("文件上传成功!");
        } else {
            return Result.error("文件上传失败!");
        }
    }
}
