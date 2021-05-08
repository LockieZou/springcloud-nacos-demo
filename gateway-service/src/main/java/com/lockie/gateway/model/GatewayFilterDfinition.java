package com.lockie.gateway.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: lockie
 * @Date: 2020/9/28 16:12
 * @Description: 路由过滤器模型
 */
@Data
public class GatewayFilterDfinition {
    // 过滤器名称
    private String name;
    // 过滤器对应的路由规则
    private Map<String, String> map = new LinkedHashMap<>();
}
