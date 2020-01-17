package com.lockie.cloudorder.client;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lockie
 * @Date: 2019/12/31 16:49
 * @Description: 获取用户信息
 */
@Slf4j
@Service
public class UserServiceClient {
    @NacosInjected
    NamingService namingService;

    private RestTemplate restTemplate = new RestTemplate();

    private static final String USER_SERVICE = "boot-user-service";
    private static final String GET_USER_NAME = "/bootUser/getUserName";

    /**
     * 获取用户名
     * @return
     */
    public String getUserName() {
        try {
            if (namingService != null) {
                // 选择 bootuser-service 服务的一个健康实例
                Instance instance = namingService.selectOneHealthyInstance(USER_SERVICE);
                // 拼接请求url
                String url = "http://" + instance.getIp() + ":" + instance.getPort() + GET_USER_NAME;
                // 采用rest方式调用user-service服务获取用户名
                ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
                return entity.getBody();
            }
        } catch (Exception e) {
            log.error("query bootuser error", e);
        }
        return null;
    }
}
