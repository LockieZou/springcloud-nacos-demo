package com.lockie.starter.config;

import com.lockie.starter.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lockie
 * @Date: 2020/11/3 15:17
 * @Description: 获取ID配置类
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(IdProperties.class)
public class IdAutoConfiguation {
    @Autowired
    private IdProperties idProperties;

    @Bean
    public IdService idService() {
        IdService idService = new IdService();
        idService.setIdProperties(idProperties);
        return idService;
    }

}
