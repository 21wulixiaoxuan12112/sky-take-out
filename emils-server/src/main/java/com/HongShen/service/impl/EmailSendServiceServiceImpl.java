package com.HongShen.service.impl;

import com.HongShen.constant.MessageConstant;
import com.HongShen.constant.StatusConstant;
import com.HongShen.entity.EmailUser;
import com.HongShen.entity.Emils;
import com.HongShen.entity.SendRecord;
import com.HongShen.exception.AccountLockedException;
import com.HongShen.exception.AccountNotFoundException;
import com.HongShen.exception.PasswordErrorException;
import com.HongShen.mapper.*;
import com.HongShen.result.Result;
import com.HongShen.service.EmailSendService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author zy
 * @date 2023/12/29 17:16
 */
@Service
public class EmailSendServiceServiceImpl implements EmailSendService {

    @Autowired
    private EmailUserMapper emailUserMapper;
    @Autowired
    private UserTemplateMapper userTemplateMapper;
    @Autowired
    private EmilsMapper emilsMapper;
    @Autowired
    private SendRecordMapper sendRecordMapper;

    @Override
    public Result send(String recipientEmail, String alias, String username, String password, String otherParams, String emailTheme) throws IOException {
//        System.out.println("username"+username);
//        System.out.println("otherParams"+otherParams);
        //根据username和password判断用户是否存在
        //1、根据用户名查询数据库中的数据
        EmailUser emailUser = emailUserMapper.getByUserName(username);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (emailUser == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
//            return Result.error("当前用户不存在!");
        }

        //密码比对
//        1.获取数据库中加盐生成的密码，
        String password1 = emailUser.getPassword();
//        2.将生成的密码破解，
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//        3.与用户输入的密码进行对比
        boolean matches = passwordEncoder.matches(password, password1);//使用passwordEncoder.matches对比即可
        if (matches == false) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
//            return Result.error("当前用户密码错误!");
        }
        if (emailUser.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
//            return Result.error("当前用户状态锁定!");

        }
        //不存在返回错误
        //存在继续根据用户id和alias查询是否存在模板
//        select alias from user_template where userid =#{emailUser.getId()} and  alias = #{alias}
        String alias1 = userTemplateMapper.getAlias(emailUser.getId(), alias);
        if (alias1 == null) {
            throw new AccountNotFoundException(MessageConstant.TEMPLATE_NOTFOUND);
//            return Result.error("当前选择模板不存在！");
        }
        //不存在返回错误
        //存在则读取模板文件内容
//        根据别名查找路径
        String path = userTemplateMapper.getPath(alias);
//        根据路径读取文件内容
        byte[] fileContent = Files.readAllBytes(Paths.get(path));
        String content = new String(fileContent);
//        System.out.println("模板内容：" + content);
        //存在继续判断otherParams是否为空
        if (emailTheme.isEmpty()) {
            throw new AccountNotFoundException(MessageConstant.THEME_NULL);
        }

        //不为空则根据参数去替换模板文件内容
//        把前端传来的数据进行html实体转义，转义结果为json字符串
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println("ccccc"+otherParams);

        String escapedHtml = StringEscapeUtils.unescapeHtml4(String.valueOf(otherParams));
//        System.out.println("json字符串：" + escapedHtml);
//          再将json字符串转换成map键值对
        // 将JSON字符串转换为Map<String, Object>
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(escapedHtml, Map.class);
//        System.out.println(map);
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            content = content.replace(key, value);
        }
//        调用方法去发送邮件
        String status = getResult(recipientEmail, alias, username, emailTheme, escapedHtml);
//        如果发送状态为1，则发送成功
        if (status == "1") {
            return Result.success("发送成功");
        } else {
//          反之亦然
            status = getResult(recipientEmail, alias, username, emailTheme, escapedHtml);
            if (status == "1") {
                return Result.success();
            } else {
                return Result.error("失败");
            }
        }


    }

    private String getResult(String recipientEmail, String alias, String username, String emailTheme, String escapedHtml) {
        //去邮箱表里面查询一条使用时间最老的一条数据
        Emils email = emilsMapper.getEmail();
        System.out.println(email);
        //修改查询到的邮箱 修改使用时间为现在
//       update email set update_time = #{update_time} where id=#{id}
        LocalDateTime now = LocalDateTime.now();
        System.out.println("邮箱id" + email.getId());
//        System.out.println(now);
        emilsMapper.updateTime(email.getId(), now);
        // //发送邮件  如果发送失败则修改邮箱状态为死亡 并且重新获取一条邮箱发送


        String username2 = email.getMailUser(); // 发件人邮箱
        System.out.println("发送人邮箱：" + username2);
        String password2 = email.getMailPassword(); // 发件人邮箱密码

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.zoho.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username2, password2);
            }
        });
        SendRecord sendRecord = new SendRecord();
        String status = "1";
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getMailUser())); // 设置发件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // 设置收件人
            message.setSubject(emailTheme); // 设置邮件主题
            message.setText(escapedHtml); // 设置邮件内容


            Transport.send(message); // 发送邮件
            sendRecord.setReceiveemail(recipientEmail);
            sendRecord.setAlias(alias);
            sendRecord.setSendemail(email.getMailUser());
            sendRecord.setSendtime(LocalDateTime.now());
            sendRecord.setStatus("1");
            sendRecord.setUsername(username);
            sendRecordMapper.save(sendRecord);
            System.out.println(sendRecord);
            System.out.println("Email sent successfully.");
//          获取发送送状态，若失败则重新发送
//            if (sendRecord.getStatus() != 1) {
//
//            }
//            email
        } catch (MessagingException e) {
            status = "0";
            email.setState(0);
            emilsMapper.startOrStop(email);
            sendRecord.setReceiveemail(recipientEmail);
            sendRecord.setAlias(alias);
            sendRecord.setSendemail(email.getMailUser());
            sendRecord.setSendtime(LocalDateTime.now());
            sendRecord.setStatus("0");
            sendRecord.setUsername(username);
            sendRecordMapper.save(sendRecord);
            System.out.println("发送邮箱：" + email);
//        修改状态
//            throw new AccountNotFoundException(MessageConstant.EMAIL_SEND_FAILED);

        }
        return status;
    }
}
