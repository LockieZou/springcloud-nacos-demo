package com.lockie.gateway.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lockie
 * @Date: 2020/9/28 16:10
 * @Description: 路由模型
 */
@Data
public class GatewayRouteDefinition {
    // 路由ID
    private String id;
    // 路由规则转发uri
    private String uri;
    // 路由执行顺序
    private int order = 0;

    // 路由断言集合
    private List<GatewayPredicateDefinition> predicates = new ArrayList<>();
    // 路由过滤器集合
    private List<GatewayFilterDfinition> filters = new ArrayList<>();
}
