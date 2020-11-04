package com.wx.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderConController {

    public static final String INVOKE_URL="http://consul-payment-server";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consul")
    public String paymentInfo()
    {
        String result=restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return result;
    }
}
