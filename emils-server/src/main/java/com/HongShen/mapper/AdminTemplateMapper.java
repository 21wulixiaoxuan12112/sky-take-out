package com.HongShen.mapper;

import com.HongShen.dto.admintemplate.AdminTemplateDTO;
import com.HongShen.dto.admintemplate.AdminTemplatePageQueryDTO;
import com.HongShen.entity.AdminTemplate;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zy
 * @date 2023/12/26 14:39
 */
@Mapper
public interface AdminTemplateMapper {

    @Insert("insert into admin_template (filename, alias, filepath, content, createtime) VALUES (#{filename},#{alias},#{filepath},#{content},#{createtime})")
    void save(AdminTemplate adminTemplate);


    Page<AdminTemplate> pageQuery(AdminTemplatePageQueryDTO adminTemplatePageQueryDTO);

    @Select("select filename, alias, filepath, content, createtime from admin_template where id = #{id}")
    AdminTemplate getById(Long id);
}
