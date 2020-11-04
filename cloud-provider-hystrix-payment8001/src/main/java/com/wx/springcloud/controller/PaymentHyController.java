package com.wx.springcloud.controller;

import com.wx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentHyController {

    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/okHy/{id}")
    public String paymentInfo_ok(@PathVariable(value = "id") Integer id){
        String result=paymentService.paymentInfo_ok(id);
        log.info("************* result： "+result);
        return  result;


    }

    @GetMapping("/timeoutHy/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        String result=paymentService.paymentInfoTimeout(id);
        log.info("************* result： "+result);
        return  result;

    }
//  ===========服务熔断
    @GetMapping("/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result=paymentService.paymentCircuitBreaker(id);
        log.info("****result:"+result);
        return  result;
    }

}
