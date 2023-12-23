package com.HongShen.mapper;

import com.HongShen.entity.EmailAdmin;
import com.HongShen.entity.EmailUser;
import org.apache.ibatis.annotations.Select;

/**
 * @author zy
 * @date 2023/12/23 17:12
 */
public interface EmailAdminMapper {
    @Select("select * from email_admin where username = #{username}")
    EmailAdmin getByUserName(String username);

    void update(EmailAdmin emailAdmin);
}
