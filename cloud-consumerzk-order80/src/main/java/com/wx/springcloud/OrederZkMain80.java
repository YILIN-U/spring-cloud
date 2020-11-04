package com.wx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrederZkMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrederZkMain80.class,args);
    }
}
