package com.lockie.cloudorder.service;


import com.lockie.cloudorder.model.ShopOrder;

/**
 * @author: lockie
 * @Date: 2019/12/31 16:48
 * @Description:
 */
public interface CloudShopOrderService {

    ShopOrder getShopOrderById(Integer id);

    /**
     * 根据订单编码查询
     * @param orderNo
     * @return
     */
    ShopOrder getShopOrderByOrderNo(String orderNo);

    int saveShopOrder(ShopOrder shopOrder);
}
