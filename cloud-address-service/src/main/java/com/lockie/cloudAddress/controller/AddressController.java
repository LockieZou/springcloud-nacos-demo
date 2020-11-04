package com.lockie.cloudAddress.controller;

import com.lockie.cloudAddress.model.ShopAddress;
import com.lockie.cloudAddress.service.CloudAddressService;
import com.lockie.common.controller.BaseController;
import com.lockie.common.model.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lockie
 * @Date: 2020/9/9 14:02
 * @Description:
 */
@RestController
@RequestMapping("/cloudAddress")
public class AddressController extends BaseController {

    @Value("${nacos.test:132}")
    String nacosTest;
    @Value("${spring.application.name}")
    String applicationName;

    @Autowired
    CloudAddressService cloudAddressService;

    /**
     * 通用接口
     * @return
     */
    @GetMapping("/helloWord")
    public Results helloWord() {
        return succeed("hello, this is " + applicationName);
    }

    @GetMapping("/getProperties")
    public Results getProperties() {
        return succeed(nacosTest);
    }


    /**
     * 根据订单ID查询地址
     * @param orderId
     * @return
     */
    @GetMapping("/getAddressByOrderId")
    public ShopAddress getAddressByOrderId(Integer orderId) {
        ShopAddress shopAddress = cloudAddressService.getAddressByOrderId(orderId);
        return shopAddress;
    }

    /**
     * 保存订单地址
     * @param shopAddress
     * @return
     */
    @PostMapping("/saveShopAddress")
    public int saveShopAddress(@RequestBody ShopAddress shopAddress) throws Exception{
        if (shopAddress == null) {
            return 0;
        }
        int i = cloudAddressService.saveShopAddress(shopAddress);
        return i;
    }
}
