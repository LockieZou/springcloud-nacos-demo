package com.lockie.cloudorder;

import com.lockie.bootorder.BootOrderServiceApplication;
import com.lockie.bootorder.service.impl.OrderSendThreadService;
import com.lockie.starter.service.IdService;
import com.lockie.starter.service.LockieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = BootOrderServiceApplication.class)
class BootOrderServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    IdService idService;
    @Autowired
    LockieService lockieService;

    @Test
    public void testIdGenerate() {
        String id = idService.getId();
        System.out.println("生成ID：" + id);

        String str = lockieService.hello("小明");
        System.out.println("打招呼："+ str);
    }

    @Autowired
    OrderSendThreadService orderSendThreadService;
    @Test
    public void sendOrderMessage() throws InterruptedException {
        // 取消订单
        orderSendThreadService.cancelOrder();
        // 发送短信
        orderSendThreadService.sendOrderPhoneMessage();
        // 发送邮件
        orderSendThreadService.sendOrderEmailMessage();
    }
}
