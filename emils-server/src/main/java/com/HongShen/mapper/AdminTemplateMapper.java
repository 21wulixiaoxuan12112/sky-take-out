package com.HongShen.mapper;

import com.HongShen.dto.admintemplate.AdminTemplateDTO;
import com.HongShen.dto.admintemplate.AdminTemplatePageQueryDTO;
import com.HongShen.dto.usertemplate.UserTemplatePageQueryDTO;
import com.HongShen.entity.AdminTemplate;
import com.HongShen.entity.UserTemplate;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author zy
 * @date 2023/12/26 14:39
 */
@Mapper
public interface AdminTemplateMapper {

//    @Insert("insert into admin_template (filename, alias, filepath, content, createtime) VALUES (#{filename},#{alias},#{filepath},#{content},#{createtime})")
//    void save(AdminTemplate adminTemplate);


    Page<UserTemplate> pageQuery(UserTemplatePageQueryDTO userTemplatePageQueryDTO);



    @Select("select id, filename, alias, createtime, filepath,userid,status from user_template where id = #{id}")
    UserTemplate getById(Long id);

    @Update("update user_template set status = #{status} where id=#{id}")
    void startOrStop(UserTemplate userTemplate);
//    AdminTemplate getById(Long id);
}
