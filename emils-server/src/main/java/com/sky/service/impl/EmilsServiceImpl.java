package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.EmilsDTO;
import com.sky.dto.EmilsPageQueryDTO;
import com.sky.entity.Emils;
import com.sky.mapper.EmilsMapper;
import com.sky.result.PageResult;
import com.sky.service.EmilsService;
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
        emils.setState(StatusConstant.DISABLE);

        emils.setMailPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        emils.setCreateTime(LocalDateTime.now());

        emils.setUpdateTime(LocalDateTime.now());

        emilsMapper.insert(emils);

    }

    //分页
    @Override
    public PageResult pageQuery(EmilsPageQueryDTO emilsPageQueryDTO) {
        // select * from employee limit 0,10
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
        emilsMapper.update(emils);
    }

    //  反显
    @Override
    public Emils getById(Long id) {
//     select mailUser,mailPassword,state,createTime,updateTime for emils where id =#{id}
       Emils emils = emilsMapper.getById(id);
       return emils;
    }
}
