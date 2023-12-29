package com.HongShen.mapper;

import com.HongShen.dto.usertemplate.UserTemplatePageQueryDTO;
import com.HongShen.entity.UserTemplate;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

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
    Integer select(String alias, Long userid);

    @Select("select id, filename, alias, createtime, filepath,userid,status from user_template where id = #{id} and userid=#{userid} ")
    UserTemplate getById(Integer id, Long userid);

    @Delete("delete from user_template where id =#{id} and userid=#{userid}")
    void deleteById(Long id, Long userid);

    @Update("update user_template set alias=#{alias},filepath=#{allPath} where id=#{id} and userid=#{currentId}")
    void update(Integer id, String alias, Long currentId, String allPath);

    @Select("select alias from user_template where userid = #{id} and alias=#{alias}")
    String getAlias(Integer id, String alias);

    @Select("select filepath from user_template where alias = #{alias} ")
    String getPath(String alias);

}