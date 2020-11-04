package com.wx.payment.controller;

import com.wx.payment.service.PaymentService;
import com.wx.springcloud.common.CommonResult;
import com.wx.springcloud.model.PaymentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    PaymentService paymentService;

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

    @GetMapping("/lb")
    public String getPaymentLb(){
        return this.serverPort;
    }
}
