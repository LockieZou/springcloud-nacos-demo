package com.lockie.cloudorder.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: lockie
 * @Date: 2019/12/31 16:44
 * @Description:
 */
@Data
public class Order implements Serializable {
    private int id;
    private String orderNo;
    private int userId;
    private String userName;
    private Date createTime;
    private double payment;
    private Date paymentDate;
    private String remark;
}
