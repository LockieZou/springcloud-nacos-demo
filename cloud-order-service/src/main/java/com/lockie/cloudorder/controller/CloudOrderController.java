package com.lockie.cloudorder.controller;

import com.lockie.cloudorder.client.CloudUserClient;
import com.lockie.cloudorder.model.ShopOrder;
import com.lockie.cloudorder.model.User;
import com.lockie.cloudorder.service.CloudShopOrderService;
import com.lockie.common.controller.BaseController;
import com.lockie.common.model.Results;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    private static final Logger logger = LoggerFactory.getLogger(CloudOrderController.class);

    @Value("${nacos.test:132}")
    String nacosTest;
    @Value("${spring.datasource.username:lockie}")
    String mysqlUserName;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CloudUserClient cloudUserClient;
    @Autowired
    CloudShopOrderService cloudShopOrderService;

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
     * 根据订单ID查询
     * @return
     */
    @GetMapping("/getOrderById")
    public Results getOrderById(Integer id) {
        ShopOrder shopOrder = cloudShopOrderService.getShopOrderById(id);

        return succeed(shopOrder);
    }

    /**
     * Feign调用方式，获取用户信息
     * @return
     */
    @GetMapping("/getRestUserName")
    public Results getRestUserName() {
        // 获取用户名
        String userName = restTemplate.getForObject("http://" + USER_SERVICE + GET_USER_NAME, String.class);

        return succeed(userName);
    }

    /**
     * Feign调用方式，获取用户信息
     * @return
     */
    @GetMapping("/getFeignUserName")
    public Results getFeignUserName() {
        String userName = cloudUserClient.getUserName();

        return succeed(userName);
    }

    /**
     * Feign调用，根据用户ID查询
     * @param userId
     * @return
     */
    @GetMapping("/getFeignUserById")
    public Results getFeignUserById(Integer userId) {
        User user = cloudUserClient.getUserById(userId);
        return succeed(user);
    }

    /**
     * 添加order
     * @param shopOrder
     * @return
     */
    @PostMapping("/addOrder")
    public Results addOrder(@RequestBody ShopOrder shopOrder) {
        if (shopOrder == null || shopOrder.getShopAddress() == null) {
            return failure("订单或者订单地址为空！");
        }
        // 查询用户是否存在
        if (shopOrder.getUserId() != null) {
            User user = cloudUserClient.getUserById(shopOrder.getUserId());
            if (user == null) {
                return failure("用户不存在！");
            }
        } else {
            return failure("用户ID为空");
        }

        int i = cloudShopOrderService.saveShopOrder(shopOrder);
        return succeed(i);
    }
}
