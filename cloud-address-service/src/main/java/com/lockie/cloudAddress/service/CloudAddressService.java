package com.lockie.cloudAddress.service;

import com.lockie.cloudAddress.model.ShopAddress;

/**
 * @author: 邹细良
 * @Date: 2020/9/9 14:03
 * @Description:
 */
public interface CloudAddressService {

    ShopAddress getAddressById(Integer id);

    /**
     * 保存地址
     * @param shopAddress
     * @return
     */
    int saveShopAddress(ShopAddress shopAddress);

    /**
     * 根据订单ID查询地址
     * @param orderId
     * @return
     */
    ShopAddress getAddressByOrderId(Integer orderId);
}
