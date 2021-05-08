package com.lockie.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lockie
 * @Date: 2020/9/28 16:01
 * @Description: 网关配置参数
 */
@Data
@Configuration
public class GatewayConfig {
    public static final long DEFAULT_TIMEOUT = 30000;

    @Value("${spring.cloud.nacos.config.server-addr}")
    public static String NACOS_SERVER_ADDR;
    @Value("${spring.cloud.nacos.discovery.namespace}")
    public static String NACOS_NAMESPACE;
    @Value("${nacos.gateway.route.config.data-id}")
    public static String NACOS_ROUTE_DATA_ID;
    @Value("${nacos.gateway.route.config.group}")
    public static String NACOS_ROUTE_GROUP;
}
