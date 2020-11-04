package com.lockie.cloudorder.service.impl;

import com.lockie.cloudorder.client.CloudAddressClient;
import com.lockie.cloudorder.client.CloudUserClient;
import com.lockie.cloudorder.mapper.ShopOrderMapper;
import com.lockie.cloudorder.model.ShopAddress;
import com.lockie.cloudorder.model.ShopOrder;
import com.lockie.cloudorder.model.User;
import com.lockie.cloudorder.service.CloudShopOrderService;
import com.lockie.common.util.DateUtil;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: lockie
 * @Date: 2020/9/8 16:24
 * @Description:
 */
@Service
public class CloudShopOrderServiceImpl implements CloudShopOrderService {
    @Autowired
    ShopOrderMapper shopOrderMapper;
    @Autowired
    CloudAddressClient cloudAddressClient;
    @Autowired
    CloudUserClient cloudUserClient;

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
     * 根据订单编号查询
      * @param orderNo
     * @return
     */
    @Override
    public ShopOrder getShopOrderByOrderNo(String orderNo) {
        if (StringUtils.isEmpty(orderNo)) {
            return null;
        }
        return shopOrderMapper.getOrderByOrderNo(orderNo);
    }

    /**
     * 新增订单
     * @param shopOrder
     * @return
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public int saveShopOrder(ShopOrder shopOrder, User user) throws Exception{

        // 生成单号
        String orderNo = DateUtil.getCurrentDateYMDHMSStr();
        shopOrder.setOrderNo(orderNo);

        shopOrder.setOrderDate(new Date());
        shopOrder.setOrderStatus(1);

        shopOrder.setCreateBy(shopOrder.getUserId());
        shopOrder.setCreateDate(new Date());
        shopOrder.setUpdateBy(shopOrder.getUserId());
        shopOrder.setUpdateDate(new Date());
        int i = shopOrderMapper.insert(shopOrder);

        // 根据单号查询
        ShopOrder order = shopOrderMapper.getOrderByOrderNo(orderNo);

        // 保存订单地址
        ShopAddress shopAddress = shopOrder.getShopAddress();
        if (shopAddress != null) {
            shopAddress.setOrderId(order.getId());
            shopAddress.setCreateBy(shopOrder.getUserId());

            int n = cloudAddressClient.saveShopAddress(shopAddress);
        }

        // 订单金额
        BigDecimal orderTotal = new BigDecimal(shopOrder.getOrderTotal());
        // 扣除用户余额
        User updateUser = cloudUserClient.decreaseMoney(user.getId(), orderTotal);

        return i;
    }
}
