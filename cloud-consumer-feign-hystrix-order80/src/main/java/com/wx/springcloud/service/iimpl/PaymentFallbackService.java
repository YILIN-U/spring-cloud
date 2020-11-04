package com.wx.springcloud.service.iimpl;

import com.wx.springcloud.service.OrderHytrixService;
import org.springframework.stereotype.Service;


@Service
public class PaymentFallbackService implements OrderHytrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "----------PaymentFallbackService paymentInfo_ok fallback ----------";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "----------PaymentFallbackService paymentInfoTimeout fallback ----------";
    }
}
