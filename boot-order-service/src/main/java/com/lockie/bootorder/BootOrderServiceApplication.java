package com.lockie.bootorder;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@MapperScan("com.lockie.bootorder.mapper")
@SpringBootApplication(scanBasePackages = "com.lockie.starter")
@NacosPropertySource(dataId = "boot-order-service", autoRefreshed = true)
public class BootOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootOrderServiceApplication.class, args);
        log.info("启动成功!");
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

}
