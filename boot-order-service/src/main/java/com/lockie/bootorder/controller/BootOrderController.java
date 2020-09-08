package com.lockie.bootorder.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.lockie.bootorder.client.UserServiceClient;
import com.lockie.bootorder.model.Results;
import com.lockie.bootorder.model.ShopOrder;
import com.lockie.bootorder.service.BootShopOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    BootShopOrderService bootShopOrderService;

    @NacosValue(value = "${nacos.test:123}", autoRefreshed = true)
    String properties;

    @RequestMapping("/getProperties")
    public Results getProperties() {
        return succeed(properties);
    }


    /**
     * 获取订单详情
     * @return
     */
    @GetMapping("/getOrderById")
    public Results getOrderById(Integer id) {
        String userName = userServiceClient.getUserName();

        ShopOrder shopOrder = bootShopOrderService.getShopOrderById(id);

        return succeed(shopOrder);
    }
}
