package com.lockie.gateway.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: lockie
 * @Date: 2020/9/28 16:13
 * @Description: 路由断言模型
 */
@Data
public class GatewayPredicateDefinition {
    // 断言名称
    private String name;
    // 断言规则
    private Map<String, String> map = new LinkedHashMap<>();
}
