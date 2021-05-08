package com.lockie.gateway.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.lockie.gateway.config.GatewayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author: lockie
 * @Date: 2020/9/28 17:51
 * @Description: 网关nacos监听类
 */
@Slf4j
@Component
@DependsOn({"gatewayConfig"})
public class DynamicRouteNacosService {
    @Autowired
    DynamicRouteServiceImpl dynamicRouteService;

    ConfigService configService;

    public DynamicRouteNacosService() {
        dynamicRouteNacosListener("gateway-service","lockie_group");
    }

    @PostConstruct
    public void init() {
        log.info("gateway route init...");
        try {
            configService = initConfigService();
            if (configService == null) {
                log.error("initConfigService fail");
                return;
            }
            String configInfo = configService.getConfig(GatewayConfig.NACOS_ROUTE_DATA_ID, GatewayConfig.NACOS_ROUTE_GROUP, GatewayConfig.DEFAULT_TIMEOUT);
            log.info("获取网关当前配置: " + configInfo);
            List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
            definitionList.forEach(definition -> {
                log.info("update route: " + definition.toString());
                dynamicRouteService.add(definition);
            });
        } catch (Exception e) {
            log.error("初始化网关路由发生错误", e);
        }
        dynamicRouteNacosListener(GatewayConfig.NACOS_ROUTE_DATA_ID, GatewayConfig.NACOS_ROUTE_GROUP);
    }

    /**
     * 监听nacos server下发的动态路由配置
     *
     * @param dataId
     * @param group
     */
    public void dynamicRouteNacosListener(String dataId, String group) {
        try {
            configService.addListener(dataId, group, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("进行网关更新：", configInfo);

                    List<RouteDefinition> definitionList = JSON.parseArray(configInfo, RouteDefinition.class);
                    definitionList.forEach(definition -> {
                        log.info("更新路由：" + definition.toString());
                        dynamicRouteService.update(definition);
                    });
                }
            });
        } catch (NacosException e) {
            log.error("下发动态路由异常！{}", e);
        }
    }

    /**
     * 初始化网关路由
     * @return
     */
    private ConfigService initConfigService() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", GatewayConfig.NACOS_SERVER_ADDR);
            properties.setProperty("namespace", GatewayConfig.NACOS_NAMESPACE);
            return configService = NacosFactory.createConfigService(properties);
        } catch (Exception e) {
            log.error("初始化网关路由异常",e);
            return null;
        }
    }
}
