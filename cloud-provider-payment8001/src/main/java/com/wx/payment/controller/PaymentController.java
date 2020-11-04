package com.wx.payment.controller;

import com.wx.payment.service.PaymentService;
import com.wx.springcloud.common.CommonResult;
import com.wx.springcloud.model.PaymentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/addPayment")
    public CommonResult addPayment(@RequestBody PaymentModel paymentModel){
        int count=paymentService.addPayment(paymentModel);
        log.info("*****插入结果:"+count);
        if (count>0)
        {
            return new CommonResult(200,"插入数据库成功"+serverPort,count);
        }else{
            return new CommonResult(444,"插入数据库失败"+serverPort,null);
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        PaymentModel data=paymentService.getPaymentById(id);
        log.info("*****插入结果:"+data+"dsada");
        if (data!=null)
        {
            return new CommonResult(200,"查询成功 serverPort"+serverPort,data);
        }else{
            return new CommonResult(444,"没有对应id可查询"+serverPort,null);
        }
    }

    @GetMapping("/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("****service: "+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SEVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getHost()+"\t"+instance.getInstanceId()+"\t"+instance.getPort()+"\t"+instance.getUri()+"\t");
        }

        return discoveryClient;
    }

    @GetMapping("/lb")
    public String getPaymentLb(){
        return this.serverPort;
    }

    @GetMapping("/timeOut")
    public String paymentFeignTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }

}
