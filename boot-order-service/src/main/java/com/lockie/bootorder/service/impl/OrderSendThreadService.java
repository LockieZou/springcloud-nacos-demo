package com.lockie.bootorder.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: lockie
 * @Date: 2021/4/25 10:53
 * @Description:
 */
@Slf4j
@Service
public class OrderSendThreadService {

    /**
     * 发送短信线程池
     * @throws InterruptedException
     */
    @Async("taskExecutor")
    public void sendOrderPhoneMessage() throws InterruptedException {
        log.info("发送短信方法------1 执行开始");
        Thread.sleep(5000);
        log.info("发送短信方法------1 执行结束");
    }

    /**
     * 发送邮件线程池
     * @throws InterruptedException
     */
    @Async("/taskExecutor")
    public void sendOrderEmailMessage() throws InterruptedException {
        log.info("发送邮件方法------2 执行开始");
        Thread.sleep(5000);
        log.info("发送邮件方法------2 执行开始");
    }

    /**
     * 取消订单接口
     */
    public void cancelOrder() {
        log.info(("取消订单的方法执行-----开始"));
        log.info(("取消订单的方法执行-----结束"));
    }
}
