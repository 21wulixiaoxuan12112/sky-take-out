package com.HongShen.mapper;

import com.HongShen.entity.EmailAdmin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zy
 * @date 2023/12/23 17:12
 */
@Mapper
public interface EmailAdminMapper {
    @Select("select * from email_admin where username = #{username}")
    EmailAdmin getByUserName(String username);

    void update(EmailAdmin emailAdmin);

    @Insert("insert into  email_admin (username, password, logintime, createtime, updatetime, token, status) VALUES (#{username},#{password},#{logintime},#{createtime},#{updatetime},#{token},#{status})")
    void insert(EmailAdmin emailAdmin);
}
