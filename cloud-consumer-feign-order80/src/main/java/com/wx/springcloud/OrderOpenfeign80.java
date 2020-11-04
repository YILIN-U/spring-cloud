package com.wx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderOpenfeign80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenfeign80.class,args);
    }
}
