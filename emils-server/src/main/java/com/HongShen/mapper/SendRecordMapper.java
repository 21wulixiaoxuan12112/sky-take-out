package com.HongShen.mapper;

import com.HongShen.entity.SendRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zy
 * @date 2023/12/29 23:56
 */
@Mapper
public interface SendRecordMapper {

    @Insert("insert into send_record(receiveemail, sendemail, alias, sendtime, status, username) VALUES (#{receiveemail},#{sendemail},#{alias},#{sendtime},#{status},#{username})")
     void save(SendRecord sendRecord);
}
