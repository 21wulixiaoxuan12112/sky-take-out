package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmilsPageQueryDTO;
import com.sky.entity.Emils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author zy
 * @date 2023/12/20 14:22
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
    @Insert("insert into email (mailUser,mailPassword,state,create_time, update_time) " + "values " + "(#{mailUser},#{mailPassword},#{state},#{createTime},#{updateTime})")
    void insert(Emils emils);

    //  修改邮箱状态
    @Update("update email set state = #{state} where id=#{id}")
    void update(Emils emils);

    //    反显
    @Select("select mailUser,mailPassword,state,createTime,updateTime for email where id =#{id}")
    Emils getById(Long id);
}
