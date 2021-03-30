package com.lockie.starter.service;

import com.lockie.starter.config.LockieProperties;
import lombok.Data;

/**
 * @author: lockie
 * @Date: 2020/11/3 18:23
 * @Description:
 */
@Data
public class LockieService {
    LockieProperties lockieProperties;

    public String hello(String name) {
        return lockieProperties.getPrefix() + "-" + name + "-" + lockieProperties.getSuffix();
    }
}
