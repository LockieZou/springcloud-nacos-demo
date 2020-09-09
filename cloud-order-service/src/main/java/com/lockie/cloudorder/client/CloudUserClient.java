package com.lockie.cloudorder.client;

import com.lockie.cloudorder.model.Results;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: lockie
 * @Date: 2020/1/17 13:43
 * @Description:
 */
@FeignClient(name = "cloud-user-service")
@RequestMapping("/cloudUser")
public interface CloudUserClient {

    /**
     * cloud-user-service服务的获取用户名接口
     * @return
     */
    @GetMapping("/getUserName")
    String getUserName();

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    @GetMapping("/getUserById")
    Results getUserById(@RequestParam("userId") Integer userId);
}
