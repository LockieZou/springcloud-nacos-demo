package com.lockie.cloudorder.aop;

import com.lockie.common.model.Results;
import io.seata.common.util.StringUtils;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: lockie
 * @Date: 2020/9/9 17:31
 * @Description: seata 动态事务回滚切面类（feign熔断降级回滚）
 */
@Slf4j
@Aspect
@Component
public class SeataAtFeignTransactionAop {

    @Before("execution(* com.lockie.cloudorder.controller.*.*(..))")
    public void before(JoinPoint joinPoint) throws TransactionException, org.springframework.transaction.TransactionException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.info("拦截的分布式事务方法：" + method.getName());

        GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
        // 设置服务之间超时时间
        tx.begin(300000, "lockie-seata");
        // 分布式xid格式: ip:port:一串数字
        log.info("创建分布式事务id:{}", tx.getXid());
    }

    @AfterThrowing(throwing = "e", pointcut = "execution(* com.lockie.cloudorder.controller.*.*(..))")
    public void doRecoveryActions(Throwable e) throws TransactionException, org.springframework.transaction.TransactionException {
        log.info("方法执行异常：{}", e.getMessage());
        if (StringUtils.isNotEmpty(RootContext.getXID())) {
            log.info("分布式事务id:{}, 手动回滚!", RootContext.getXID());
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        }
    }

    @AfterReturning(value = "execution(* com.lockie.cloudorder.controller.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) throws TransactionException, org.springframework.transaction.TransactionException {
        log.info("方法执行结束：{}", result);
        // 方法返回值Results自定义的
        Results results = (Results) result;

        if (!results.isStatus()) {
            if (StringUtils.isNotEmpty(RootContext.getXID())) {
                log.info("分布式事务id:{},手动回滚", RootContext.getXID());
                GlobalTransactionContext.reload(RootContext.getXID()).rollback();
            }
        }
    }
}
