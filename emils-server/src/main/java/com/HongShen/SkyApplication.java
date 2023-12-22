package com.HongShen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
@EnableCaching//开发缓存注解功能
@EnableScheduling //开启任务调度
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SkyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyApplication.class, args);
//        log.info("server started");
    }
}

