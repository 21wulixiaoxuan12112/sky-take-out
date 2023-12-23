package com.HongShen.service.impl;

import com.HongShen.constant.MessageConstant;
import com.HongShen.constant.StatusConstant;
import com.HongShen.dto.EmilsUserDTO;
import com.HongShen.dto.EmilsUserLoginDTO;
import com.HongShen.dto.EmilsUserPageQueryDTO;
import com.HongShen.entity.EmailUser;
import com.HongShen.entity.Emils;
import com.HongShen.exception.AccountLockedException;
import com.HongShen.exception.AccountNotFoundException;
import com.HongShen.exception.PasswordErrorException;
import com.HongShen.mapper.EmailUserMapper;
import com.HongShen.result.PageResult;
import com.HongShen.service.EmailUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author author
 * @since 2023-12-21
 */
@Service
public class EmailUserServiceImpl implements EmailUserService {
    @Autowired
    private EmailUserMapper emailUserMapper;

    @Override
    public EmailUser getById(Long id) {
//        return emailUserMapper.getById(id);
        return emailUserMapper.getById(id);
    }

    @Override
    public void save(EmilsUserDTO emilsUserDTO) {
        EmailUser emailUser = new EmailUser();
        BeanUtils.copyProperties(emilsUserDTO, emailUser);
//        Spring Security加盐
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //        数据库密码
        String finalPassword = passwordEncoder.encode(emilsUserDTO.getPassword());
        emailUser.setPassword(finalPassword);

        emailUser.setCreateTime(LocalDateTime.now());
//        emailUser.setUpdatetime(LocalDateTime.now());
        emailUser.setStatus(0);
        emailUserMapper.insert(emailUser);
    }

    @Override
    public EmailUser login(EmilsUserLoginDTO emilsUserLoginDTO) {
        String username = emilsUserLoginDTO.getUsername();
        String password = emilsUserLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
//        Employee employee = employeeMapper.getByUsername(username);
        EmailUser emailUser = emailUserMapper.getByUserName(username);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (emailUser == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //对前端传过来的明文密码进行md5加密处理
//        password = DigestUtils.md5DigestAsHex(password.getBytes());

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.matches(password,finalPassword);//使用passwordEncoder.matches对比即可

        if (!password.equals(emailUser.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (emailUser.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return emailUser;
    }

    @Override
    public void startOrStop(Integer status, Integer id) {
        //update emils set state = ? where id=?
        EmailUser emailUser = EmailUser.builder()
                .status(status)
                .id(id)
                .build();
        emailUserMapper.startOrStop(emailUser);
    }

    @Override
    public void update(EmilsUserDTO emilsUserDTO) {
        //-- 修改数据
        //update email set mail_user=#{mailUser},mail_password=#{mailPassword},update_time=#{updateTime} where id=#{id}
        EmailUser emailUser = new EmailUser();
        BeanUtils.copyProperties(emilsUserDTO, emailUser);
        emailUser.setUpdateTime(LocalDateTime.now());
        emailUserMapper.update(emailUser);
    }

    @Override
    public void deleteById(Long id) {
        emailUserMapper.deleteById(id);
    }

    @Override
    public PageResult pageQuery(EmilsUserPageQueryDTO emilsUserPageQueryDTO) {
//        开启分页
        PageHelper.startPage(emilsUserPageQueryDTO.getPage(), emilsUserPageQueryDTO.getPageSize());
        Page<EmailUser> page = emailUserMapper.pageQuery(emilsUserPageQueryDTO);
        long total = page.getTotal();
        List<EmailUser> result = page.getResult();
        return new PageResult(total, result);
    }
}
