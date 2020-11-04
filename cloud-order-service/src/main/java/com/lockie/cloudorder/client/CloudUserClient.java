package com.lockie.cloudorder.client;

import com.lockie.cloudorder.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author: lockie
 * @Date: 2020/1/17 13:43
 * @Description: 用户服务接口
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
    User getUserById(@RequestParam("userId") Integer userId);

    /**
     * 增加用户账户金额
     * @param userId
     * @param accountMoney
     * @return
     */
    @GetMapping("/increaseMoney")
    User increaseMoney(@RequestParam("userId") Integer userId, @RequestParam("accountMoney") BigDecimal accountMoney);

    /**
     * 减少用户账户金额
     *
     * @param userId
     * @param accountMoney
     * @return
     * @throws Exception
     */
    @GetMapping("/decreaseMoney")
    User decreaseMoney(@RequestParam("userId") Integer userId, @RequestParam("accountMoney") BigDecimal accountMoney) throws Exception;
}
