package com.HongShen.service.impl;

import com.HongShen.constant.MessageConstant;
import com.HongShen.entity.Emils;
import com.HongShen.result.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.HongShen.constant.PasswordConstant;
import com.HongShen.constant.StatusConstant;
import com.HongShen.dto.email.EmilsDTO;
import com.HongShen.dto.email.EmilsPageQueryDTO;
import com.HongShen.mapper.EmilsMapper;
import com.HongShen.result.PageResult;
import com.HongShen.service.EmilsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author zy
 * @date 2023/12/20 15:28
 */
@Service
public class EmilsServiceImpl implements EmilsService {

    @Autowired
    private EmilsMapper emilsMapper;

    //验证邮箱格式
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    /*
     * 新增*/
    @Override
    public Result save(EmilsDTO emilsDTO) {
        Emils emils = new Emils();
//
//        邮箱名字相同且smtp相同则不可以新增
//        select count(*) from emils  where mail_user=#{mailUser} and smtp_serveraddress=#{smtpServeraddress}
        Integer number = emilsMapper.getNumber(emilsDTO.getMailUser(), emilsDTO.getSmtpServeraddress());
        if (number > 0) {
            return Result.error("当前邮箱名/SMTP服务器地址已存在！");
        }
        if (!isValidEmail(emilsDTO.getMailUser())) {
            return Result.error("邮箱格式不正确!");
        }
        BeanUtils.copyProperties(emilsDTO, emils);

        emils.setState(StatusConstant.ENABLE);

        emils.setCreateTime(LocalDateTime.now());

        emils.setUpdateTime(LocalDateTime.now());

        emilsMapper.insert(emils);
        return Result.success("新建邮箱成功！");
    }

    //分页
    @Override
    public PageResult pageQuery(EmilsPageQueryDTO emilsPageQueryDTO) {
        // select * from emails limit 0,10
        //开始分页查询
        PageHelper.startPage(emilsPageQueryDTO.getPage(), emilsPageQueryDTO.getPageSize());

        Page<Emils> page = emilsMapper.pageQuery(emilsPageQueryDTO);

        long total = page.getTotal();
        List<Emils> records = page.getResult();
        return new PageResult(total, records);
    }

    //修改邮箱状态
    @Override
    public void startOrStop(Integer state, Integer id) {
        //update emils set state = ? where id=?
        Emils emils = Emils.builder()
                .state(state)
                .id(id)
                .build();
        emilsMapper.startOrStop(emils);
    }

    //  反显
    @Override
    public Emils getById(Long id) {
        // select mailUser,mailPassword,state,createTime,updateTime for emils where id =#{id}
        return emilsMapper.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        emilsMapper.deleteById(id);
    }

    //修改
    @Override
    public void update(EmilsDTO emilsDTO) {
        //-- 修改数据
        //update email set mail_user=#{mailUser},mail_password=#{mailPassword},update_time=#{updateTime} where id=#{id}
        Emils emils = new Emils();
        BeanUtils.copyProperties(emilsDTO, emils);
        emils.setUpdateTime(LocalDateTime.now());
        emilsMapper.update(emils);
    }
}
