package com.lockie.cloudorder;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@NacosPropertySource(dataId = "boot-order-service", autoRefreshed = true)
public class BootOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootOrderServiceApplication.class, args);
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

}
