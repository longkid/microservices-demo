package com.xyz.orderservice.service;

import com.xyz.orderservice.dto.CarOrderDto;
import com.xyz.orderservice.dto.Response;

public interface OrderService {
    Response createOrder(CarOrderDto order);

    Response getAllOrders();

    Response retrieveCarInfo(long orderId);
}
