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
import java.util.List;
import java.util.Properties;

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
                            @RequestParam List<String> otherParams,
                            @RequestParam String emailTheme
                            ){
        emailSendService.send(recipientEmail,alias,username,password,otherParams,emailTheme);
        //根据username和password判断用户是否存在
        //不存在返回错误
        //存在继续根据用户id和alias查询是否存在模板
        //不存在返回错误
        //存在则读取模板文件内容
        //存在继续判断otherParams是否为空
        //不为空则根据参数去替换模板文件内容
        //去邮箱表里面查询一条使用时间最老的一条数据
        //修改查询到的邮箱 修改使用时间为现在
        //发送邮件  如果发送失败则修改邮箱状态为死亡 并且重新获取一条邮箱发送

//       String username = "your-email@your-domain.com"; // 发件人邮箱
//        String password = "your-password"; // 发件人邮箱密码

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.zoho.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your-email@your-domain.com")); // 设置发件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("recipient-email@recipient-domain.com")); // 设置收件人
            message.setSubject("Test Email"); // 设置邮件主题
            message.setText("This is a test email."); // 设置邮件内容

            Transport.send(message); // 发送邮件

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
      return Result.success("邮件发送成功");

    }




//


}
