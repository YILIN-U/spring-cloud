package com.wx.springcloud.openfeign;

import com.wx.springcloud.common.CommonResult;
import com.wx.springcloud.model.PaymentModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient(value = "CLOUD-PAYMENT-SEVICE")
public interface PaymentFeign {
    @GetMapping("/payment/getPaymentById/{id}")
    CommonResult<PaymentModel> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/timeOut")
    String paymentFeignTimeOut();
}
