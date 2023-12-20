package com.sky.controller.admin;

/**
 * @author zy
 * @date 2023/12/20 14:19
 */

import com.sky.dto.EmilsDTO;
import com.sky.dto.EmilsPageQueryDTO;
import com.sky.entity.Emils;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        log.info("新增邮箱：{}",emilsDTO);
        emilsService.save(emilsDTO);
        return Result.success();
    }
    /**
     * 邮箱分页查询
     * @param  emilsPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("邮箱分页查询")
    public Result<PageResult> page(EmilsPageQueryDTO emilsPageQueryDTO){
        log.info("员工分页查询，参数为：{}",  emilsPageQueryDTO);
        PageResult pageResult = emilsService.pageQuery( emilsPageQueryDTO);
        return Result.success(pageResult);
    }
    /**
     * 启用禁用邮箱账号
     * @param state
     * @param id
     * @return
     */
    @PostMapping("/state/{state}")
    @ApiOperation("启用禁用邮箱账号")
    public Result startOrStop(@PathVariable Integer state,Integer id){
        log.info("启用禁用员工账号：{},{}",state,id);
        emilsService.startOrStop(state,id);
        return Result.success();
    }
    /**
     * 反显
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("反显")
    public Result<Emils> getById(@PathVariable Long id){
        Emils emils = emilsService.getById(id);
        return Result.success(emils);
    }
}
