package com.lockie.cloudorder.aop;

import com.lockie.common.controller.BaseController;
import com.lockie.common.model.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import javax.validation.ConstraintViolation;

/**
 * @author: lockie
 * @Date: 2020/9/9 17:48
 * @Description:
 */
@Slf4j
@Configuration
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice
public class GloabExceptionAdvice extends BaseController {

    /**
     * 全局异常处理
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Results errorHandler(Exception e) throws Exception {
        // 异常错误处理
        if (e instanceof ConstraintViolation) {
            log.info("绑定错误日志:{}", e.getMessage());
            return failure("请求数据格式错误");
        } else if (e instanceof MethodArgumentNotValidException) {
            log.info("方法级绑定错误日志：{}",e.getMessage());
            return failure("请求数据格式错误");
        } else {
            log.info("其它错误日志:{}", e.getMessage());
            return failure("未知错误");
        }
    }
}
