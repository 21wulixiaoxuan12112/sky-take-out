package com.HongShen.controller;

import com.HongShen.result.Result;
import com.HongShen.service.EmailSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
/**
 * @author zy
 * @date 2023/12/29 16:53
 */
@RestController
@RequestMapping("/emailsend")
@Slf4j
@Api(tags = "邮箱发送接口")
public class EmailSendController {
    @Autowired
    private EmailSendService emailSendService;

    @PostMapping
    @ApiOperation("邮件发送")
    public Result sendEmail(@ApiParam(value = "接收者的电子邮件地址", required = true)
                                     @RequestParam String recipientEmail,

                            @ApiParam(value = "模板别名", required = true)
                                     @RequestParam String alias,

                            @ApiParam(value = "用户名", required = true)
                                     @RequestParam String username,

                            @ApiParam(value = "密码", required = true)
                                     @RequestParam String password,

                            @ApiParam(value = "其他参数")
                                     @RequestParam String otherParams,

                            @ApiParam(value = "电子邮件主题", required = true)
                                     @RequestParam String emailTheme
                            ) throws IOException {
        return emailSendService.send(recipientEmail,alias,username,password,otherParams,emailTheme);


    }
}
