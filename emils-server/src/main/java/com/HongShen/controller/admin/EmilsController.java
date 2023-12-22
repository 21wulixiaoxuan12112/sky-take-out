package com.HongShen.controller.admin;

/**
 * @author zy
 * @date 2023/12/20 14:19
 */

import com.HongShen.dto.EmilsDTO;
import com.HongShen.dto.EmilsPageQueryDTO;
import com.HongShen.entity.Emils;
import com.HongShen.result.PageResult;
import com.HongShen.result.Result;
import com.HongShen.service.EmilsService;
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
     *
     * @param emilsDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增邮箱")
    public Result save(@RequestBody EmilsDTO emilsDTO) {
        log.info("新增邮箱：{}", emilsDTO);
        emilsService.save(emilsDTO);
        return Result.success();
    }

    /**
     * 邮箱分页查询
     *
     * @param emilsPageQueryDTO
     * @return
     */

    @GetMapping("/page")
    @ApiOperation("邮箱分页查询")
    public Result<PageResult> page(EmilsPageQueryDTO emilsPageQueryDTO) {
        log.info("邮箱分页查询，参数为：{}", emilsPageQueryDTO);
        PageResult pageResult = emilsService.pageQuery(emilsPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用邮箱账号
     *
     * @param state
     * @param id
     * @return
     */
    @PostMapping("/state/{state}")
    @ApiOperation("启用禁用邮箱账号")
    public Result startOrStop(@PathVariable Integer state, Integer id) {
        log.info("启用禁用邮箱账号：{},{}", state, id);
        emilsService.startOrStop(state, id);
        return Result.success();
    }

    /**
     * 反显
     // @param id
     *
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("反显")
    public Result<Emils> getById(@PathVariable Long id) {
        Emils emils = emilsService.getById(id);
        return Result.success(emils);
    }

    /**
     * 编辑邮箱信息
     * //  @param emilsDTO
     * //  @return
     */
    @PutMapping
    @ApiOperation("编辑邮箱信息")
    public Result update(@RequestBody EmilsDTO emilsDTO) {
        log.info("编辑邮箱信息：{}", emilsDTO);
        emilsService.update(emilsDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除")
    public Result<Emils> deleteById(@PathVariable Long id) {
        emilsService.deleteById(id);
        return Result.success();
    }

}
