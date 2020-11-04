package com.wx.payment.service;


import com.wx.springcloud.model.PaymentModel;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int addPayment(PaymentModel paymentModel);

    PaymentModel getPaymentById(@Param("id") Long id);
}
