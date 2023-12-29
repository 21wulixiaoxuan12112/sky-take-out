package com.HongShen.service;

import com.HongShen.mapper.EmailSendMapper;
import com.HongShen.mapper.UserTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zy
 * @date 2023/12/29 17:13
 */
public interface EmailSendService {

    void send(String recipientEmail, String alias, String username, String password, List<String> otherParams);
}
