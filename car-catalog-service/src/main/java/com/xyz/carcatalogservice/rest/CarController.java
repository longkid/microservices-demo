package com.xyz.carcatalogservice.rest;

import com.xyz.carcatalogservice.dto.CarDto;
import com.xyz.carcatalogservice.dto.CarOrderDto;
import com.xyz.carcatalogservice.dto.Response;
import com.xyz.carcatalogservice.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {
    private static final Logger log = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @GetMapping
    public Response getAllCars() {
        log.info("Get all cars API is called");
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Response getCar(@PathVariable long id) {
        log.info("Get a car API is called");
        return carService.getCar(id);
    }

    @PostMapping
    public Response createCar(@RequestBody CarDto car) {
        log.info("Create a car API is called");
        return carService.createCar(car);
    }

    @PutMapping("/{id}")
    public Response updateCar(@PathVariable long id, @RequestBody CarDto car) {
        log.info("Update a car API is called");
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public Response deleteCar(@PathVariable long id) {
        log.info("Delete a car API is called");
        return carService.deleteCar(id);
    }

    @PostMapping("/order")
    public Response placeOrder(@RequestBody CarOrderDto order) {
        log.info("Place order API is called");
        return carService.placeOrder(order);
    }
}
