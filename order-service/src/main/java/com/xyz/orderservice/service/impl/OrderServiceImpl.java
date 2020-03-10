package com.xyz.orderservice.service.impl;

import com.xyz.orderservice.dto.CarOrderDto;
import com.xyz.orderservice.dto.Response;
import com.xyz.orderservice.entity.CarOrder;
import com.xyz.orderservice.repository.CarOrderRepository;
import com.xyz.orderservice.service.OrderService;
import com.xyz.orderservice.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Lazy
public class OrderServiceImpl implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private CarOrderRepository carOrderRepository;

    @Autowired
    private RestUtil restUtil;

    @Value("${carCatalogServiceUrl}")
    private String carCatalogServiceUrl;

    @Override
    public Response createOrder(CarOrderDto order) {
        Optional<CarOrder> orderOpt = carOrderRepository.findByCarId(order.getCarId());
        if (orderOpt.isPresent()) {
            String msg = String.format("Car having id '%s' was ordered before", order.getCarId());
            log.info(msg);
            return Response.builder().success(false).msg(msg).build();
        }
        carOrderRepository.save(new CarOrder(order));
        log.info("Succeed to place order for carId '{}'", order.getCarId());
        return Response.builder().success(true).build();
    }

    @Override
    public Response getAllOrders() {
        List<CarOrder> allOrders = carOrderRepository.findAll();
        return Response.builder().success(true).result(allOrders).build();
    }

    @Override
    public Response retrieveCarInfo(long orderId) {
        Optional<CarOrder> orderOpt = carOrderRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            String msg = String.format("Order having id '%s' does not exist", orderId);
            log.info(msg);
            return Response.builder().success(false).msg(msg).build();
        }

        CarOrder carOrder = orderOpt.get();
        Response response = restUtil.doGet(carCatalogServiceUrl + "/cars/" + carOrder.getCarId());
        if (!response.isSuccess()) {
            log.info("Fail to create order: {}", response.getMsg());
            return Response.builder().success(false).msg(response.getMsg()).build();
        }
        return Response.builder().success(true).result(response.getResult()).build();
    }
}
