package com.HongShen.mapper;

import com.HongShen.dto.user.EmilsUserPageQueryDTO;
import com.HongShen.entity.EmailUser;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

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
    EmailUser getById(Long id);

    @Delete("delete from email_user where id=#{id} ")
    void deleteById(Long id);

    void update(EmailUser emailUser);

    @Update("update email_user set status = #{status} where id=#{id}")
    void startOrStop(EmailUser emailUser);

    //获取用户姓名

    /**
     * 根据用户名查询员工
     * // @param username
     *
     * @return
     */
    @Select("select * from email_user where username = #{username}")
    EmailUser getByUserName(String username);

    @Select("select id from email_user where username = #{username}")
    Integer getId(String username);

    @Select("select count(*) as 'number' from email_user where username = #{username}")
    Integer getNumber(String username);


//    @Select("select count(*) as 'number' from email_user where username = #{username}")
//    Integer getByUserNameCount(String username);
//
//    @Select("select password from email_user where username = #{username}")
//    String getPassword(String username);
}
