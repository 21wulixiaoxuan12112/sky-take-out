package com.HongShen.mapper;

import com.HongShen.dto.usertemplate.UserTemplatePageQueryDTO;
import com.HongShen.entity.UserTemplate;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zy
 * @date 2023/12/26 20:12
 */
@Mapper
public interface UserTemplateMapper {

    @Insert("insert into emails.user_template(filename, alias, filepath, createtime,userid,status) VALUES (#{filename},#{alias},#{filepath},#{createtime},#{userid},#{status})")
    void save(UserTemplate userTemplate);

    Page<UserTemplate> pageQuery(UserTemplatePageQueryDTO userTemplatePageQueryDTO);


    @Select("select count(*) as 'number' from emails.user_template where alias=#{alias} and userid=#{userid} ")
    Integer select(String alias,Long userid);

}