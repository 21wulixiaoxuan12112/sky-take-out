package com.HongShen.controller.admin;

/**
 * @author zy
 * @date 2023/12/23 17:01
 */

import com.HongShen.constant.JwtClaimsConstant;
import com.HongShen.dto.Login.EmilsAdminLoginDTO;
import com.HongShen.dto.Login.EmilsUserLoginDTO;
import com.HongShen.entity.EmailAdmin;
import com.HongShen.entity.EmailUser;
import com.HongShen.properties.JwtProperties;
import com.HongShen.result.Result;
import com.HongShen.service.EmailAdminService;
import com.HongShen.service.EmailUserService;
import com.HongShen.utils.JwtUtil;
import com.HongShen.vo.Login.EmilAdminLoginVO;
import com.HongShen.vo.Login.EmilUserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员账号管理
 */
@RestController
@RequestMapping("/admin/emils")
@Slf4j
@Api(tags = "管理员登录接口")
public class EmilsAdminLoginController {
    @Autowired
    private EmailAdminService emailAdminService;

    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 登录
     *
     * @param emilsAdminLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public Result<EmilAdminLoginVO> login(@RequestBody EmilsAdminLoginDTO emilsAdminLoginDTO) {
        log.info("管理员登录：{}", emilsAdminLoginDTO);

        EmailAdmin emailAdmin = emailAdminService.login(emilsAdminLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, emailAdmin.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        log.info("token："+token);
        EmilAdminLoginVO emilAdminLoginVO = EmilAdminLoginVO.builder()
                .id(emailAdmin.getId())
                .userName(emailAdmin.getUsername())
                .token(token)
                .build();

        return Result.success(emilAdminLoginVO);
    }


}
