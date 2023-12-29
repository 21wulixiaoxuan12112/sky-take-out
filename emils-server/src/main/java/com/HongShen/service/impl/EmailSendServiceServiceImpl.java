package com.HongShen.service.impl;

import com.HongShen.mapper.EmailSendMapper;
import com.HongShen.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zy
 * @date 2023/12/29 17:16
 */
@Service
public class EmailSendServiceServiceImpl implements EmailSendService {
    @Autowired
    private EmailSendMapper emailSendMapper;

    @Override
    public void send(String recipientEmail, String alias, String username, String password, List<String> otherParams) {

    }
}
