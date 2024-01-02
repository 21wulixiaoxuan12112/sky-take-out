package com.HongShen.mapper;

import com.HongShen.entity.Emils;
import com.github.pagehelper.Page;
import com.HongShen.dto.email.EmilsPageQueryDTO;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

/**
 * @author zy
 * @date 2023/12/20 15:22
 */
@Mapper
public interface EmilsMapper {
    /*
     * 分页查询
     * */
    Page<Emils> pageQuery(EmilsPageQueryDTO emilsPageQueryDTO);

    /*
     * 新增邮箱
     * */
    @Insert("insert into email (mail_user,mail_password,state,create_time, update_time,port_umber,smtp_serveraddress) values (#{mailUser},#{mailPassword},#{state},#{createTime},#{updateTime},#{portUmber},#{smtpServeraddress})")
    void insert(Emils emils);

    //  修改邮箱状态
    @Update("update email set state = #{state} where id=#{id}")
    void startOrStop(Emils emils);

    //    反显
    @Select("select mail_user,mail_password,state,create_time,update_time,port_umber,smtp_serveraddress from email where id = #{id}")
    Emils getById(Long id);

    //删除
    @Delete("delete from email where id=#{id} ")
    void deleteById(Long id);

    //修改邮箱
    void update(Emils emils);


    @Select("select id,mail_user,mail_password,state,create_time,update_time,port_umber,smtp_serveraddress from email where state=1 order by update_time desc limit 1")
    Emils getEmail();


    @Update("update email set update_time = #{now} where id=#{id}")
    void updateTime(Integer id, LocalDateTime now);

    @Select("select count(*) as 'number' from email where mail_user=#{mailUser} and smtp_serveraddress=#{smtpServeraddress}")
    Integer getNumber(String mailUser, String smtpServeraddress);
}
