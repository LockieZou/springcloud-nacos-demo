package com.lockie.bootorder.controller;

import com.lockie.common.controller.BaseController;
import com.lockie.common.model.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lockie
 * @Date: 2019/12/19 18:15
 * @Description:
 */
@RestController
public class PingController extends BaseController {

    @GetMapping("/ping")
    public Results ping() {
        return succeed("pong");
    }
}
