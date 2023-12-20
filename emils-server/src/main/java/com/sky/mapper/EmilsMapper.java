package com.sky.mapper;

import com.sky.entity.Emils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zy
 * @date 2023/12/20 14:22
 */
@Mapper
public interface EmilsMapper {
    /*
     * 新增邮箱
     * */
    @Insert("insert into employee (mailUser,mailPassword,state,create_time, update_time) " +
            "values " +
            "(#{mailUser},#{mailPassword},#{state},#{createTime},#{updateTime})")
    void insert(Emils emils);
}
