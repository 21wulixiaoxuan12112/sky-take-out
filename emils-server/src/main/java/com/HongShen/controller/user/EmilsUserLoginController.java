package com.HongShen.controller.user;

import com.HongShen.constant.JwtClaimsConstant;
import com.HongShen.dto.Login.EmilsUserLoginDTO;
import com.HongShen.entity.EmailUser;
import com.HongShen.properties.JwtProperties;
import com.HongShen.result.Result;
import com.HongShen.service.EmailUserService;
import com.HongShen.utils.JwtUtil;
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
 * @author zy
 * @date 2023/12/22 17:13
 */

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user/emils")
@Slf4j
@Api(tags = "用户登录接口")
public class EmilsUserLoginController {
    @Autowired
    private EmailUserService emailUserService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param emilsUserLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result<EmilUserLoginVO> login(@RequestBody EmilsUserLoginDTO emilsUserLoginDTO) {
        log.info("用户登录：{}", emilsUserLoginDTO);

        EmailUser emailUser = emailUserService.login(emilsUserLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, emailUser.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

       EmilUserLoginVO employeeLoginVO = EmilUserLoginVO.builder()
                .id(emailUser.getId())
                .userName(emailUser.getUsername())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }


}
