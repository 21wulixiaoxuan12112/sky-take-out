package com.HongShen.mapper;

import com.github.pagehelper.Page;
import com.HongShen.dto.email.EmilsPageQueryDTO;
import com.HongShen.entity.Emils;
import org.apache.ibatis.annotations.*;

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
    @Insert("insert into email (mail_user,mail_password,state,create_time, update_time) values (#{mailUser},#{mailPassword},#{state},#{createTime},#{updateTime})")
    void insert(Emils emils);

    //  修改邮箱状态
    @Update("update email set state = #{state} where id=#{id}")
    void startOrStop(Emils emils);

    //    反显
    @Select("select mail_user,mail_password,state,create_time,update_time from email where id = #{id}")
    Emils getById(Long id);

    //删除
    @Delete("delete from email where id=#{id} ")
    void deleteById(Long id);

    //修改邮箱
    void update(Emils emils);
}
