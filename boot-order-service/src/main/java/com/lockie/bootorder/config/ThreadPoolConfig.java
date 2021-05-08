package com.lockie.bootorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: lockie
 * @Date: 2021/4/25 09:20
 * @Description: 多线程配置
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    /**
     * 默认情况下，在创建线程池后，线程池中的线程数为0，当任务来后就会创建一个线程去执行任务
     * 当线程中的线程数目达到corePoolSize后，就会吧到达的任务放到缓存队列中
     * 当缓存队列满了，就会继续创建线程，当线程数量大于等于maxPoolSize后，就开始使用拒绝策略拒绝线程创建
     */

    // 核心线程数，默认线程数
    private static final int corePoolSize = 10;
    // 最大线程数
    private static final int maxPoolSize = 100;
    // 允许线程空闲时间，单位默认秒
    private static final int keepAliveTime = 10;
    // 缓冲队列大小
    private static final int queueCapacity = 20;
    // 线程池名前缀
    private static final String threadNamePrefix = "Async-service-";

    /**
     * 创建线程池
     * @return
     */
    @Bean("/taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setThreadGroupName(threadNamePrefix);

        /**
         * 线程满了后的拒绝任务处理策略
         * callerRunPolicy 由调用线程(提交任务的线程)处理该任务
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        // TODO 处理自己的逻辑
        return executor;
    }

}

