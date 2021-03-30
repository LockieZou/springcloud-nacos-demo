package com.lockie.starter.service;

import com.lockie.starter.config.IdProperties;
import lombok.Data;

/**
 * @author: lockie
 * @Date: 2020/11/3 15:19
 * @Description: 生成ID接口
 */
@Data
public class IdService {

    private IdProperties idProperties;

    /**
     * 获取ID
     * @return
     */
    public String getId() {
        return idProperties.getName() + "-" + System.currentTimeMillis();
    }
}
