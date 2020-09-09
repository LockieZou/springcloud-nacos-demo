package com.lockie.cloudorder.service.impl;

import com.lockie.cloudorder.mapper.ShopOrderMapper;
import com.lockie.cloudorder.model.ShopOrder;
import com.lockie.cloudorder.service.CloudShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: 邹细良
 * @Date: 2020/9/8 16:24
 * @Description:
 */
@Service
public class CloudShopOrderServiceImpl implements CloudShopOrderService {
    @Autowired
    ShopOrderMapper shopOrderMapper;

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public ShopOrder getShopOrderById(Integer id) {
        if (id == null) {
            return null;
        }
        return shopOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增订单
     * @param shopOrder
     * @return
     */
    @Override
    public int saveShopOrder(ShopOrder shopOrder) {
        shopOrder.setCreateBy(1);
        shopOrder.setCreateDate(new Date());
        shopOrder.setUpdateBy(1);
        shopOrder.setUpdateDate(new Date());
        return shopOrderMapper.insert(shopOrder);
    }
}
