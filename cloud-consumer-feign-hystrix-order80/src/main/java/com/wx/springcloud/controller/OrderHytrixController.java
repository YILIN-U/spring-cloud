package com.wx.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wx.springcloud.service.OrderHytrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
@DefaultProperties(defaultFallback ="payment_Global_FallbackMethod" )
public class OrderHytrixController {

    @Autowired
    private OrderHytrixService orderHytrixService;

    @GetMapping("/okHy/{id}")
    public String paymentInfo_ok(@PathVariable(value = "id") Integer id){

        return orderHytrixService.paymentInfo_ok(id);

    }

//    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties  ={
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    @GetMapping("/timeoutHy/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id){

//        int a=10/0;

        return orderHytrixService.paymentInfoTimeout(id);

    }
    public String paymentInfoTimeoutHandler(Integer id){

        return  "我是端口80，对方的支付系统繁忙请10秒钟之后尝试或者自己运行错误请重视";
    }

//    global fallback 全局的配置
    public String payment_Global_FallbackMethod(){
        return "global处理异常，请稍后再试";
    }

}
