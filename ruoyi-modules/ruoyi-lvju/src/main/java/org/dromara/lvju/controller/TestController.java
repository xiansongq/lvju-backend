package org.dromara.lvju.controller;

import org.dromara.common.web.core.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")

public class TestController extends BaseController {

    @GetMapping("/get")
    private void test(){
        System.out.println("这是一个测试控制器");
    }
}
