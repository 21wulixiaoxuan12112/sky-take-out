package com.HongShen.service;

import com.HongShen.result.Result;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author zy
 * @date 2023/12/29 17:13
 */
public interface EmailSendService {

    Result send(String recipientEmail, String alias, String username, String password, String otherParams, String emailTheme) throws IOException;
}
