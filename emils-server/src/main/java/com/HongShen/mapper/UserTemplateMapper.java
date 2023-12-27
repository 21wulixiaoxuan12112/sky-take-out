package com.HongShen.mapper;

import com.HongShen.entity.UserTemplate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zy
 * @date 2023/12/26 20:12
 */
@Mapper
public interface UserTemplateMapper {

    @Insert("insert into user_template (filename, alias, filepath, content, createtime) VALUES (#{filename},#{alias},#{filepath},#{content},#{createtime})")
    void save(UserTemplate userTemplate);
}
