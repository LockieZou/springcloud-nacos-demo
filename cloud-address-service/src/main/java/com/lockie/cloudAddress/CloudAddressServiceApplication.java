package com.lockie.cloudAddress;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@MapperScan("com.lockie.cloudAddress.mapper")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CloudAddressServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudAddressServiceApplication.class, args);
    }

}
