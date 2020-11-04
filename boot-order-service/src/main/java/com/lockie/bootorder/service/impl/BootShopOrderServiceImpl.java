package com.lockie.bootorder.service.impl;

import com.lockie.bootorder.mapper.ShopOrderMapper;
import com.lockie.bootorder.model.ShopOrder;
import com.lockie.bootorder.service.BootShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lockie
 * @Date: 2020/9/8 16:24
 * @Description:
 */
@Service
public class BootShopOrderServiceImpl implements BootShopOrderService {
    @Autowired
    ShopOrderMapper shopOrderMapper;

    @Override
    public ShopOrder getShopOrderById(Integer id) {
        if (id == null) {
            return null;
        }
        return shopOrderMapper.selectByPrimaryKey(id);
    }
}
