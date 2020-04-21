package com.lockie.cloudorder.controller;

import com.lockie.cloudorder.client.CloudUserClient;
import com.lockie.cloudorder.model.Order;
import com.lockie.cloudorder.model.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author: lockie
 * @Date: 2020/1/10 16:25
 * @Description:
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/cloudOrder")
public class CloudOrderController extends BaseController {

    @Value("${nacos.test:132}")
    String nacosTest;
    @Value("${spring.datasource.username:lockie}")
    String mysqlUserName;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CloudUserClient cloudUserClient;

    private final static String USER_SERVICE = "cloud-user-service";
    private final static String GET_USER_NAME = "/cloudUser/getUserName";

    /**
     * 获取配置文件内容
     * @return
     */
    @GetMapping("/getProperties")
    public Results getProperties() {
        return succeed(nacosTest);
    }

    /**
     * 获取mysql数据库配置用户名
     * @return
     */
    @GetMapping("/getMysqlProperties")
    public Results getMysqlProperties() {
        return succeed(mysqlUserName);
    }

    /**
     * RestTemplate调用方式，获取订单信息
     * @return
     */
    @GetMapping("/getOrder")
    public Results getOrder() {
        // 获取用户名
        String userName = restTemplate.getForObject("http://" + USER_SERVICE + GET_USER_NAME, String.class);

        Order order = new Order();
        order.setId(1);
        order.setUserName(userName);
        order.setCreateTime(new Date());
        order.setRemark("RestTemplate调用方式获取用户信息");

        return succeed(order);
    }

    /**
     * Feign调用方式，获取订单信息
     * @return
     */
    @GetMapping("/getFeignOrder")
    public Results getFeignOrder() {
        String userName = cloudUserClient.getUserName();

        Order order = new Order();
        order.setId(2);
        order.setUserName(userName);
        order.setCreateTime(new Date());
        order.setRemark("Feign调用方式获取用户信息");

        return succeed(order);
    }

    /**
     * 添加order
     * @param order
     * @return
     */
    @PostMapping("/addOrder")
    public Results addOrder(@RequestBody Order order) {
        order.setRemark("手动添加order");
        return succeed(order);
    }
}
