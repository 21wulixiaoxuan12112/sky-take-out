package com.HongShen.controller.admin;

import com.HongShen.dto.admintemplate.AdminTemplateDTO;
import com.HongShen.dto.admintemplate.AdminTemplatePageQueryDTO;
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
public class TemplateController {
    @Autowired
    private AdminTemplateService adminTemplateService;

    @PostMapping
    @ApiOperation("新增模板")
    public Result saveUser(@RequestBody AdminTemplateDTO adminTemplateDTO) throws IOException {
        log.info("新增模板：{}", adminTemplateDTO);
        adminTemplateService.save(adminTemplateDTO);
        return Result.success();
    }

    @GetMapping("/pageTemplate")
    @ApiOperation("模板分页")
    public Result<PageResult> page(AdminTemplatePageQueryDTO adminTemplatePageQueryDTO) {
        log.info("分页模板数据{}", adminTemplatePageQueryDTO);
        PageResult pageResult = adminTemplateService.pageQuery(adminTemplatePageQueryDTO);
        return Result.success(pageResult);
    }
}
