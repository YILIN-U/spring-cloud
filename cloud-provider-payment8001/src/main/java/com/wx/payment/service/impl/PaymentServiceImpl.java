package com.wx.payment.service.impl;

import com.wx.payment.dao.PaymentDao;
import com.wx.payment.service.PaymentService;

import com.wx.springcloud.model.PaymentModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    public int addPayment(PaymentModel paymentModel){

        return paymentDao.addPayment(paymentModel);

    }

    public PaymentModel getPaymentById( Long id){

        return paymentDao.getPaymentById(id);

    }
}
