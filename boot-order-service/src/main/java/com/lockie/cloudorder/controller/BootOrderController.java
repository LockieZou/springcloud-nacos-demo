package com.lockie.cloudorder.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.lockie.cloudorder.client.UserServiceClient;
import com.lockie.cloudorder.model.Order;
import com.lockie.cloudorder.model.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: lockie
 * @Date: 2019/12/31 16:43
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/bootOrder")
public class BootOrderController extends BaseController {

    @Autowired
    UserServiceClient userServiceClient;

    @NacosValue(value = "${nacos.test:123}", autoRefreshed = true)
    String properties;

    @RequestMapping("/getProperties")
    public Results getProperties() {
        return succeed(properties);
    }


    /**
     * 获取订单
     * @return
     */
    @GetMapping("/getOrder")
    public Results getOrder() {
        String userName = userServiceClient.getUserName();

        Order order = new Order();
        order.setId(1);
        order.setUserName(userName);
        order.setCreateTime(new Date());

        return succeed(order);
    }
}
