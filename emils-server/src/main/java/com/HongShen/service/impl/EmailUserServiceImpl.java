package com.HongShen.service.impl;

import com.HongShen.dto.EmilsUserDTO;
import com.HongShen.dto.EmilsUserPageQueryDTO;
import com.HongShen.entity.EmailUser;
import com.HongShen.entity.Emils;
import com.HongShen.mapper.EmailUserMapper;
import com.HongShen.result.PageResult;
import com.HongShen.service.EmailUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        emailUser.setStatus("0");
        emailUserMapper.insert(emailUser);
    }

    @Override
    public void startOrStop(String status, Integer id) {
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
