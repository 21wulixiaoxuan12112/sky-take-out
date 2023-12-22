package com.HongShen.controller.admin;


import com.HongShen.dto.EmilsDTO;
import com.HongShen.dto.EmilsUserDTO;
import com.HongShen.dto.EmilsUserPageQueryDTO;
import com.HongShen.entity.Emils;
import com.HongShen.result.PageResult;
import com.HongShen.result.Result;
import com.HongShen.service.EmailUserService;
import com.HongShen.service.EmilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author zy
 * @since 2023-12-21
 */
@RestController
@RequestMapping("/email-user")
@Slf4j
@Api(tags = "会员相关接口")
public class EmailUserController {
    @Autowired
    private EmailUserService emailUserService;

//    新增会员

    @PostMapping
    @ApiOperation("新增会员")
    public Result save(@RequestBody EmilsUserDTO emilsUserDTO) {
        log.info("新增邮箱：{}", emilsUserDTO);
        emailUserService.save(emilsUserDTO);
        return Result.success();
    }

    //    用户分页
    @GetMapping("/page")
    @ApiOperation("用户分页")
    public Result<PageResult> page(EmilsUserPageQueryDTO emilsUserPageQueryDTO) {
        log.info("分页用户数据{}", emilsUserPageQueryDTO);
        PageResult pageResult = emailUserService.pageQuery(emilsUserPageQueryDTO);
        return Result.success(pageResult);
    }

    //    反显
    @GetMapping("/{id}")
    @ApiOperation("反显")
    public Result<Emils> getById(@PathVariable Long id) {
        Emils emils = emailUserService.getById(id);
        return Result.success(emils);
    }
}
