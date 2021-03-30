package com.lockie.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: lockie
 * @Date: 2020/11/3 15:10
 * @Description:
 */
@Data
@ConfigurationProperties("machine.id")
public class IdProperties {
    private String name;
}
