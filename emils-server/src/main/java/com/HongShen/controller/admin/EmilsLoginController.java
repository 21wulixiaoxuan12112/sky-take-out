//package com.HongShen.controller.admin;
//
//import com.HongShen.properties.JwtProperties;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author zy
// * @date 2023/12/22 17:13
// */
//
///**
// * 员工管理
// */
//@RestController
//@RequestMapping("/admin/emils")
//@Slf4j
//@Api(tags = "管理用户登录接口")
//public class EmilsLoginController {
//
//
//    @Autowired
//    private JwtProperties jwtProperties;
//
//    /**
//     * 登录
//     *
//     * @param employeeLoginDTO
//     * @return
//     */
//    @PostMapping("/login")
//    @ApiOperation(value = "管理员登录")
//    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
//        log.info("员工登录：{}", employeeLoginDTO);
//
//        Employee employee = employeeService.login(employeeLoginDTO);
//
//        //登录成功后，生成jwt令牌
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
//        String token = JwtUtil.createJWT(
//                jwtProperties.getAdminSecretKey(),
//                jwtProperties.getAdminTtl(),
//                claims);
//
//        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
//                .id(employee.getId())
//                .userName(employee.getUsername())
//                .name(employee.getName())
//                .token(token)
//                .build();
//
//        return Result.success(employeeLoginVO);
//    }
//
//
//}
