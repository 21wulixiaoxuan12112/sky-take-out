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
    public Emils getById(Long id) {
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
    public PageResult pageQuery(EmilsUserPageQueryDTO emilsUserPageQueryDTO) {
//        开启分页
        PageHelper.startPage(emilsUserPageQueryDTO.getPage(),emilsUserPageQueryDTO.getPageSize());
        Page<EmailUser> page =emailUserMapper.pageQuery(emilsUserPageQueryDTO);
        long total = page.getTotal();
        List<EmailUser> result = page.getResult();
        return new PageResult(total,result);
    }
}
