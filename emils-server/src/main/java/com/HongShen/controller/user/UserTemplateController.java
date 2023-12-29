package com.HongShen.controller.user;


import com.HongShen.dto.usertemplate.UserTemplatePageQueryDTO;
import com.HongShen.entity.UserTemplate;
import com.HongShen.result.PageResult;
import com.HongShen.result.Result;
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
    public Result saveUserTemplate(@RequestParam("file") MultipartFile file, String alias) throws IOException {
        log.info("新增模板：{}", file);
        if (!file.isEmpty()) {
            return Result.success(userTemplateService.set(file, alias));
        } else {
            return Result.error("文件为空，上传失败！");
        }
    }

    //    分页
    @GetMapping("/pageTemplate")
    @ApiOperation("模板分页")
    public Result<PageResult> page(UserTemplatePageQueryDTO userTemplatePageQueryDTO) {
        log.info("分页模板数据{}", userTemplatePageQueryDTO);
        PageResult pageResult = userTemplateService.pageQuery(userTemplatePageQueryDTO);
        return Result.success(pageResult);
    }

    @PutMapping
    @ApiOperation("替换模板")
    public Result updateUser(@RequestPart("file") MultipartFile file,String alias,Integer id) throws IOException {
        log.info("替换模板信息", file);
        userTemplateService.update(id,file,alias);
        return Result.success();
    }
    @GetMapping("/{id}")
    @ApiOperation("查看模板")
    public Result<UserTemplate> getById(@PathVariable Integer id) {
        UserTemplate userTemplate = userTemplateService.getById(id);
//        AdminTemplate adminTemplate = adminTemplateService.getById(id);
//        EmailUser emailUser = emailUserService.getById(id);
        return Result.success(userTemplate);
    }
    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public Result<UserTemplate> deleteById(@PathVariable Long id) {
        userTemplateService.deleteById(id);
        return Result.success();
    }

}