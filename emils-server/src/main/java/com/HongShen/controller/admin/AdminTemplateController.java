package com.HongShen.controller.admin;

import com.HongShen.dto.admintemplate.AdminTemplateDTO;
import com.HongShen.dto.admintemplate.AdminTemplatePageQueryDTO;
import com.HongShen.dto.usertemplate.UserTemplatePageQueryDTO;
import com.HongShen.entity.AdminTemplate;
import com.HongShen.entity.EmailUser;
import com.HongShen.entity.UserTemplate;
import com.HongShen.result.PageResult;
import com.HongShen.result.Result;
import com.HongShen.service.AdminTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author zy
 * @date 2023/12/26 14:19
 */
@RestController
@RequestMapping("/admin/emils/template")
@Slf4j
@Api(tags = "模板管理相关接口")
public class AdminTemplateController {
    @Autowired
    private AdminTemplateService adminTemplateService;

//    @PostMapping
//    @ApiOperation("新增模板")
//    public Result saveAdminTemplate(@RequestBody AdminTemplateDTO adminTemplateDTO) throws IOException {
//        log.info("新增模板：{}", adminTemplateDTO);
//        adminTemplateService.save(adminTemplateDTO);
//        return Result.success();
//    }

    @GetMapping("/pageTemplate")
    @ApiOperation("模板分页查看")
    public Result<PageResult> page(UserTemplatePageQueryDTO userTemplatePageQueryDTO) {
        log.info("分页模板数据{}", userTemplatePageQueryDTO);
        PageResult pageResult = adminTemplateService.pageQuery(userTemplatePageQueryDTO);
        return Result.success(pageResult);
    }

    //    反显
    @GetMapping("/{id}")
    @ApiOperation("查看模板")
    public Result<UserTemplate> getById(@PathVariable Long id) {
        UserTemplate userTemplate = adminTemplateService.getById(id);
//        AdminTemplate adminTemplate = adminTemplateService.getById(id);
//        EmailUser emailUser = emailUserService.getById(id);
        return Result.success(userTemplate);
    }

    @PostMapping("/state/{state}")
    @ApiOperation("启用禁用模板")
    public Result startOrStop(@PathVariable Integer status, Integer id) {
        log.info("启用禁用邮箱账号：{},{}", status, id);
        adminTemplateService.startOrStop(status, id);
        return Result.success();
    }
}
