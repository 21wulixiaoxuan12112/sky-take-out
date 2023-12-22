package com.HongShen.mapper;

import com.HongShen.dto.EmilsUserPageQueryDTO;
import com.HongShen.entity.EmailUser;
import com.HongShen.entity.Emils;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2023-12-21
 */
@Mapper
public interface EmailUserMapper {
    @Insert("insert into  email_user (username, password, login_time, create_time, update_time, token, status) VALUES (#{username},#{password},#{loginTime},#{createTime},#{updateTime},#{token},#{status})")
    void insert(EmailUser emailUser);

    Page<EmailUser> pageQuery(EmilsUserPageQueryDTO emilsUserPageQueryDTO);

    @Select("select username,password,login_time,create_time,update_time,status from email_user where id = #{id}")
    Emils getById(Long id);
}
