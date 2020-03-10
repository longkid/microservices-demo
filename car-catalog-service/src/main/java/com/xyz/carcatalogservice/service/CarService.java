package com.xyz.carcatalogservice.service;

import com.xyz.carcatalogservice.dto.CarDto;
import com.xyz.carcatalogservice.dto.CarOrderDto;
import com.xyz.carcatalogservice.dto.Response;
import com.xyz.carcatalogservice.entity.Car;

import java.util.List;

public interface CarService {
    Response getAllCars();

    Response placeOrder(CarOrderDto order);

    Response getCar(long id);

    Response createCar(CarDto car);

    Response updateCar(long id, CarDto car);

    Response deleteCar(long id);
}
