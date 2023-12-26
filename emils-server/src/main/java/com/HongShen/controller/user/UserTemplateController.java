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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result saveUserTemplate(@RequestBody UserTemplateDTO userTemplateDTO) throws IOException {
        log.info("新增模板：{}", userTemplateDTO);
        userTemplateService.save(userTemplateDTO);
        return Result.success();
    }
}
