package com.lockie.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author: lockie
 * @Date: 2020/9/28 16:16
 * @Description: 动态路由实现类
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {
    @Autowired
    RouteDefinitionWriter routeDefinitionWriter;

    ApplicationEventPublisher publisher;

    // 增加路由
    public String add(RouteDefinition routeDefinition) {
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
        return "route create success";
    }

    // 更新路由
    public String update(RouteDefinition routeDefinition) {
        // 先删除路由，再创建
        try {
            routeDefinitionWriter.delete(Mono.just(routeDefinition.getId())).subscribe();
        } catch (Exception e) {
            return "route update fail, not find route routeId: " + routeDefinition.getId();
        }

        try {
            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "route update success";
        } catch (Exception e) {
            return "route update fail";
        }
    }

    // 删除路由
    public Mono<ResponseEntity<Object>> delete(RouteDefinition routeDefinition) {
        return routeDefinitionWriter.delete(Mono.just(routeDefinition.getId()))
                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build()));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
