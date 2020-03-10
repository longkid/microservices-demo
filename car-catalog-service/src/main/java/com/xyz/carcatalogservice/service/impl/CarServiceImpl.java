package com.xyz.carcatalogservice.service.impl;

import com.xyz.carcatalogservice.dto.Action;
import com.xyz.carcatalogservice.dto.CarDto;
import com.xyz.carcatalogservice.dto.CarOrderDto;
import com.xyz.carcatalogservice.dto.Response;
import com.xyz.carcatalogservice.entity.Car;
import com.xyz.carcatalogservice.entity.LogAction;
import com.xyz.carcatalogservice.repository.CarRepository;
import com.xyz.carcatalogservice.repository.LogActionRepository;
import com.xyz.carcatalogservice.service.CarService;
import com.xyz.carcatalogservice.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Lazy
public class CarServiceImpl implements CarService {
    private static final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LogActionRepository logActionRepository;

    @Autowired
    private RestUtil restUtil;

    @Value("${orderServiceUrl}")
    private String orderServiceUrl;

    @Override
    public Response getAllCars() {
        List<Car> allCars = carRepository.findAll();
        return Response.builder().success(true).result(allCars).build();
    }

    @Override
    public Response placeOrder(CarOrderDto order) {
        // Check if requested car exists
        Optional<Car> carOpt = carRepository.findById(order.getCarId());
        if (!carOpt.isPresent()) {
            String msg = String.format("Car having id '%s' does not exist", order.getCarId());
            log.info(msg);
            return Response.builder().success(false).msg(msg).build();
        }

        // Call order service
        Response response = restUtil.doPost(orderServiceUrl + "/orders", order);
        if (response.isSuccess()) {
            log.info("Succeed to place order for carId '{}'", order.getCarId());
            Car car = carOpt.get();
            car.setOrdered(true);
            carRepository.save(car);
        } else {
            log.error("Fail to place order for carId '{}': {}", order.getCarId(), response.getMsg());
        }

        LogAction logAction = LogAction.builder()
                .success(response.isSuccess()).action(Action.PLACE_ORDER.name()).actionTime(LocalDateTime.now()).build();
        logActionRepository.save(logAction);
        return response;
    }

    @Override
    public Response getCar(long id) {
        Optional<Car> carOpt = carRepository.findById(id);
        if (!carOpt.isPresent()) {
            String msg = String.format("Car having id '%s' does not exist", id);
            log.info(msg);
            return Response.builder().success(false).msg(msg).build();
        }

        Car car = carOpt.get();
        car.setViewCount(car.getViewCount() + 1);
        carRepository.save(car);
        return Response.builder().success(true).result(new CarDto(car)).build();
    }

    @Override
    public Response createCar(CarDto car) {
        carRepository.save(new Car(car));
        return Response.builder().success(true).build();
    }

    @Override
    public Response updateCar(long id, CarDto car) {
        if (!carRepository.existsById(id)) {
            String msg = String.format("Car having id '%s' does not exist", id);
            log.info(msg);
            return Response.builder().success(false).msg(msg).build();
        }

        Car newCar = new Car(car);
        newCar.setId(id);
        carRepository.save(newCar);
        return Response.builder().success(true).build();
    }

    @Override
    public Response deleteCar(long id) {
        if (!carRepository.existsById(id)) {
            String msg = String.format("Car having id '%s' does not exist", id);
            log.info(msg);
            return Response.builder().success(false).msg(msg).build();
        }

        carRepository.deleteById(id);
        return Response.builder().success(true).build();
    }
}
