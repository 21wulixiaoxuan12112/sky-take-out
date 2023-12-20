package com.sky.controller.admin;

/**
 * @author zy
 * @date 2023/12/20 14:19
 */

import com.sky.dto.EmilsDTO;
import com.sky.result.Result;
import com.sky.service.EmilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮箱管理
 */
@RestController
@RequestMapping("/admin/emils")
@Slf4j
@Api(tags = "邮箱相关接口")
public class EmilsController {

    @Autowired
    private EmilsService emilsService;

    /**
     * 新增员工
     * @param emilsDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增邮箱")
    public Result save(@RequestBody EmilsDTO emilsDTO){
        log.info("新增员工：{}",emilsDTO);
        emilsService.save(emilsDTO);
        return Result.success();
    }
}
