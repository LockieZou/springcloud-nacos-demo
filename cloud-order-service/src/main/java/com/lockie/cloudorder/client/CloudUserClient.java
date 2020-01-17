package com.lockie.cloudorder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: lockie
 * @Date: 2020/1/17 13:43
 * @Description:
 */
@FeignClient(name = "cloud-user-service")
public interface CloudUserClient {

    /**
     * cloud-user-service服务的获取用户名接口
     * @return
     */
    @GetMapping("/cloudUser/getUserName")
    String getUserName();
}
