package com.wx.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    String consulPort;

    @RequestMapping("/consul")
    public String paymentZk(){
        return "springCloud with consul: " + consulPort+"\t"+ UUID.randomUUID().toString();//流水号
    }
}
