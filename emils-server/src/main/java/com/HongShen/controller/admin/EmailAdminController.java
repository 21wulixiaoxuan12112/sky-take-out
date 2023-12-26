package com.HongShen.controller.admin;

import com.HongShen.dto.Login.EmilsAdminDTO;
import com.HongShen.result.Result;
import com.HongShen.service.EmailAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zy
 * @date 2023/12/23 18:06
 */
@RestController
@RequestMapping("/email-admin")
@Slf4j
@Api(tags = "管理员相关接口")
public class EmailAdminController {
    @Autowired
    private EmailAdminService emailAdminService;

//    新增会员

    @PostMapping
    @ApiOperation("新增管理员")
    public Result save(@RequestBody EmilsAdminDTO emilsAdminDTO) {
        log.info("新增管理员：{}", emilsAdminDTO);
        emailAdminService.save(emilsAdminDTO);
        return Result.success();
    }
}
