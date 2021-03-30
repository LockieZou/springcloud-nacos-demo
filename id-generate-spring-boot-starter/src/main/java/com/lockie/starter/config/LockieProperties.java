package com.lockie.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: lockie
 * @Date: 2020/11/3 18:21
 * @Description:
 */
@Data
@ConfigurationProperties("spring.lockie")
public class LockieProperties {
    private String prefix;
    private String suffix;
}
