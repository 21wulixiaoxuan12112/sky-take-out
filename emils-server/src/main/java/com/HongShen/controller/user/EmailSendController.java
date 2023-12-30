package com.HongShen.controller.user;

import com.HongShen.result.Result;
import com.HongShen.service.EmailSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
    public Result sendEmail(@RequestParam String recipientEmail,
                            @RequestParam String alias,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String otherParams,
                            @RequestParam String emailTheme
                            ) throws IOException {
        emailSendService.send(recipientEmail,alias,username,password,otherParams,emailTheme);
      return Result.success("邮件发送成功");

    }
}
