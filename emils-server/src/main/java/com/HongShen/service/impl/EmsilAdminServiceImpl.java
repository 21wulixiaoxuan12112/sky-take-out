package com.HongShen.service.impl;

import com.HongShen.constant.MessageConstant;
import com.HongShen.constant.StatusConstant;
import com.HongShen.dto.Login.EmilsAdminDTO;
import com.HongShen.dto.Login.EmilsAdminLoginDTO;
import com.HongShen.entity.EmailAdmin;
import com.HongShen.exception.AccountLockedException;
import com.HongShen.exception.AccountNotFoundException;
import com.HongShen.exception.PasswordErrorException;
import com.HongShen.mapper.EmailAdminMapper;
import com.HongShen.service.EmailAdminService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/23 17:09
 */
@Service
public class EmsilAdminServiceImpl implements EmailAdminService {
    @Autowired
    private EmailAdminMapper emailAdminMapper;

    //        Spring Security加盐
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public EmailAdmin login(EmilsAdminLoginDTO emilsAdminLoginDTO) {
        String username = emilsAdminLoginDTO.getUsername();
        String password = emilsAdminLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        EmailAdmin emailAdmin = emailAdminMapper.getByUserName(username);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (emailAdmin == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
//        1.获取数据库中加盐生成的密码，
        String password1 = emailAdmin.getPassword();
//        2.将生成的密码破解，
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//        3.与用户输入的密码进行对比
        boolean matches = passwordEncoder.matches(password, password1);//使用passwordEncoder.matches对比即可
        if (matches == false) {
//        if (!password.equals(emailUser.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (emailAdmin.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        emailAdmin.setLogintime(LocalDateTime.now());
        emailAdminMapper.update(emailAdmin);

        //3、返回实体对象
        return emailAdmin;
    }

    @Override
    public void save(EmilsAdminDTO emilsAdminDTO) {
        EmailAdmin emailAdmin = new EmailAdmin();
        BeanUtils.copyProperties(emilsAdminDTO, emailAdmin);

        //        数据库密码
        String finalPassword = passwordEncoder.encode(emilsAdminDTO.getPassword());
        emailAdmin.setPassword(finalPassword);

        emailAdmin.setCreatetime(LocalDateTime.now());
//        emailUser.setUpdatetime(LocalDateTime.now());
        emailAdmin.setStatus(1);
        emailAdminMapper.insert(emailAdmin);
    }
}
