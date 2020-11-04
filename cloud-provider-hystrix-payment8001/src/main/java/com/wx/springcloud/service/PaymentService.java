package com.wx.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id){

        return  "线程池： "+Thread.currentThread().getName()+" paymentInfoId： "+id;

    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties  ={
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String paymentInfoTimeout(Integer id){
//        int age=10/0;

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e)  {
            e.printStackTrace();
        }
        return  "线程池： "+Thread.currentThread().getName()+" paymentInfoTimeoutId： "+id+"耗时(秒）: ";
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return  "线程池： "+Thread.currentThread().getName()+"系统运行超时或者错误 ： "+id+"\t"+"我就是兜底";
    }

//    服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少跳闸
    })
    public  String  paymentCircuitBreaker(Integer id)
    {
        if (id<0)
        {
            throw new RuntimeException("*********id不能为负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread .currentThread().getName()+"\t"+"调用成功,流水号: " +serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数,请稍后再试,id: "+id;
    }


}
