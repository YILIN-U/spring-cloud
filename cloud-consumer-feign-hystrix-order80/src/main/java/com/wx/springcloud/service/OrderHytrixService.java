package com.wx.springcloud.service;

import com.wx.springcloud.service.iimpl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERICE",fallback = PaymentFallbackService.class)
public interface OrderHytrixService {

    @GetMapping("/payment/okHy/{id}")
    String paymentInfo_ok(@PathVariable(value = "id") Integer id);

    @GetMapping("/payment/timeoutHy/{id}")
    String paymentInfoTimeout(@PathVariable("id") Integer id);

}
