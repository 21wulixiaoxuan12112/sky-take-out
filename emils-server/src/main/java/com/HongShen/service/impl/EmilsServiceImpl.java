package com.HongShen.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.HongShen.constant.PasswordConstant;
import com.HongShen.constant.StatusConstant;
import com.HongShen.dto.EmilsDTO;
import com.HongShen.dto.EmilsPageQueryDTO;
import com.HongShen.entity.Emils;
import com.HongShen.mapper.EmilsMapper;
import com.HongShen.result.PageResult;
import com.HongShen.service.EmilsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zy
 * @date 2023/12/20 15:28
 */
@Service
public class EmilsServiceImpl implements EmilsService {

    @Autowired
    private EmilsMapper emilsMapper;

    /*
     * 新增*/
    @Override
    public void save(EmilsDTO emilsDTO) {
        Emils emils = new Emils();
        BeanUtils.copyProperties(emilsDTO, emils);
//
        emils.setState(StatusConstant.ENABLE);

        emils.setMailPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        emils.setCreateTime(LocalDateTime.now());

        emils.setUpdateTime(LocalDateTime.now());

        emilsMapper.insert(emils);

    }

    //  TODO
    //分页
    @Override
    public PageResult pageQuery(EmilsPageQueryDTO emilsPageQueryDTO) {
        // select * from emails limit 0,10
        //开始分页查询
        PageHelper.startPage(emilsPageQueryDTO.getPage(), emilsPageQueryDTO.getPageSize());

        Page<Emils> page = emilsMapper.pageQuery(emilsPageQueryDTO);

        long total = page.getTotal();
        List<Emils> records = page.getResult();
        PageResult pageResult = new PageResult(total, records);
        return pageResult;
    }

    //  TODO
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
