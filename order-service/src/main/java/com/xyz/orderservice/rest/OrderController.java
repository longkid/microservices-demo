package com.xyz.orderservice.rest;

import com.xyz.orderservice.dto.CarOrderDto;
import com.xyz.orderservice.dto.Response;
import com.xyz.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Response createOrder(@RequestBody CarOrderDto order) {
        log.info("Create order API is called");
        return orderService.createOrder(order);
    }

    @GetMapping
    public Response getAllOrders() {
        log.info("Get all orders API is called");
        return orderService.getAllOrders();
    }

    @GetMapping("/retrieve-car-info/{orderId}")
    public Response retrieveCarInfo(@PathVariable long orderId) {
        log.info("Get all orders API is called");
        return orderService.retrieveCarInfo(orderId);
    }
}
