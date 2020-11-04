package com.wx.springcloud.controller;


import com.wx.springcloud.common.CommonResult;
import com.wx.springcloud.model.PaymentModel;
import com.wx.springcloud.openfeign.PaymentFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderFeignController {
    @Autowired
    private PaymentFeign paymentFeign;

    @GetMapping("/payment/{id}")
    public CommonResult<PaymentModel> getPaymentByFeign(@PathVariable("id") Long id){
        CommonResult<PaymentModel> paymentModel = paymentFeign.getPaymentById(id);
        if (paymentModel.getData()!=null){
            return paymentModel;
        }else {
            return new CommonResult(444,"操作失败");
        }
    }

    @GetMapping("/timeOut")
    public String paymentFeignTimeOut(){
        String serverPort = paymentFeign.paymentFeignTimeOut();
        return serverPort;
    }

}
