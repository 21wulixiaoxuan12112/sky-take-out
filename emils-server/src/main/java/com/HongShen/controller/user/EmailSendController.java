package com.HongShen.controller.user;

import com.HongShen.result.Result;
import com.HongShen.service.EmailSendService;
import com.HongShen.service.UserTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
                            @RequestParam List<String> otherParams){
        emailSendService.send(recipientEmail,alias,username,password,otherParams);
      return Result.success("邮件发送成功");
    }
}
