package com.wx.order80.controller;


import com.wx.order80.lb.LoadBalancer;
import com.wx.springcloud.common.CommonResult;
import com.wx.springcloud.model.PaymentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

//    public static final String  PAYMENT_URL="http://localhost:8001";
    public static final String  PAYMENT_URL="http://CLOUD-PAYMENT-SEVICE";
    
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancer loadBalancer;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/addPayment")
    public CommonResult addPayment(PaymentModel paymentModel){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/addPayment",paymentModel,CommonResult.class);
    }

    @GetMapping("/addPaymentEntity")
    public CommonResult addPaymentEntity(PaymentModel paymentModel){
        ResponseEntity<CommonResult> entity=restTemplate.postForEntity(PAYMENT_URL+"/payment/addPayment",paymentModel,CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult(444,"操作失败");
        }
    }


    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id,CommonResult.class);
    }

    @GetMapping("/getForEntity/{id}")
    public CommonResult getForEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity=restTemplate.getForEntity(PAYMENT_URL+"/payment/getPaymentById/"+id,CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult(444,"操作失败");
        }
    }

    @GetMapping("/lb")
    public String getInstanceByBalance(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SEVICE");
        if (instances==null||instances.size()==0){
            return null;
        }
        ServiceInstance instance  = loadBalancer.instances(instances);
        URI uri = instance.getUri();
        String serverPort = restTemplate.getForObject(uri + "/payment/lb", String.class);

        return serverPort;

    }



}
