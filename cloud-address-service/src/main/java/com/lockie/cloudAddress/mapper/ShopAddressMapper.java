package com.lockie.cloudAddress.mapper;

import com.lockie.cloudAddress.model.ShopAddress;

public interface ShopAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopAddress record);

    int insertSelective(ShopAddress record);

    ShopAddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopAddress record);

    int updateByPrimaryKey(ShopAddress record);

    ShopAddress getAddressByOrderId(Integer orderId);
}