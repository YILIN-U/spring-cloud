package com.wx.payment.dao;


import com.wx.springcloud.model.PaymentModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface PaymentDao {
    int addPayment(PaymentModel paymentModel);

    PaymentModel getPaymentById(@Param("id") Long id);
}
