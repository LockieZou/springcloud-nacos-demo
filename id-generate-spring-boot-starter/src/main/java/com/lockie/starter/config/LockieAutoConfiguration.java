package com.lockie.starter.config;

import com.lockie.starter.service.LockieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lockie
 * @Date: 2020/11/3 18:26
 * @Description:
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties({LockieProperties.class})
public class LockieAutoConfiguration {
    @Autowired
    LockieProperties lockieProperties;

    @Bean
    public LockieService lockieService() {
        LockieService lockieService = new LockieService();
        lockieService.setLockieProperties(lockieProperties);
        return lockieService;
    }
}
