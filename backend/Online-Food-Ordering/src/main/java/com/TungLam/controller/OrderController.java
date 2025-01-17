package com.TungLam.controller;

import com.TungLam.model.CartItem;
import com.TungLam.model.Order;
import com.TungLam.model.User;
import com.TungLam.request.AddCartItemRequest;
import com.TungLam.request.OrderRequest;
import com.TungLam.response.PaymentResponse;
import com.TungLam.service.OrderService;
import com.TungLam.service.PaymentService;
import com.TungLam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @PostMapping("/orders")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req,
                                                       @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Order order=orderService.createOrder(req, user);
        PaymentResponse res = paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
                                             @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders=orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.OK);

    }


}
