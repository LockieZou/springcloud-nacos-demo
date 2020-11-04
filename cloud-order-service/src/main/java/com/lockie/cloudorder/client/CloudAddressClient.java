package com.lockie.cloudorder.client;

import com.lockie.cloudorder.model.ShopAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: lockie
 * @Date: 2020/9/9 15:11
 * @Description: 订单地址服务接口
 */
@FeignClient(name = "cloud-address-service")
@RequestMapping("/cloudAddress")
public interface CloudAddressClient {

    /**
     * 根据订单ID查询地址
     * @param orderId
     * @return
     */
    @GetMapping("/getAddressByOrderId")
    ShopAddress getAddressByOrderId(Integer orderId);

    /**
     * 保存订单地址
     * @param shopAddress
     * @return
     */
    @PostMapping("/saveShopAddress")
    int saveShopAddress(@RequestBody ShopAddress shopAddress);

}
