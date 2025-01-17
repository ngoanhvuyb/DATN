package com.TungLam.service;

import com.TungLam.model.Order;
import com.TungLam.response.PaymentResponse;
import com.stripe.exception.StripeException;
import lombok.Data;


public interface PaymentService{
    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}