package com.razorpay.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.payload.OrderDetails;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class Paymentcontroller {
    //http://localhost:8080/api/order
    @PostMapping
    public String createOrder(
            @RequestBody OrderDetails orderDetails
    ) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_1q22Nw10KDk2p6", "xviyokHGEKldvWfvQtJcItC6");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount",orderDetails.getAmmount());
        orderRequest.put("currency",orderDetails.getCurrency());
        orderRequest.put("receipt", UUID.randomUUID().toString());
        JSONObject notes = new JSONObject();
        notes.put(orderDetails.getNoteSubject(),orderDetails.getNoteContent());

        orderRequest.put("notes",notes);

        Order order = razorpay.orders.create(orderRequest);
        return order.get("id").toString();
    }
}
