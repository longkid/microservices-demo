package com.xyz.carcatalogservice.service;

import com.xyz.carcatalogservice.entity.Car;
import com.xyz.carcatalogservice.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class InitDBService {
    private static final Logger log = LoggerFactory.getLogger(InitDBService.class);

    @Autowired
    private CarRepository carRepository;

    @PostConstruct
    public void initDB() {
        log.info("Init DB for testing");
        List<Car> cars = Arrays.asList(
                Car.builder().make("Kia").model("Morning").colour("Red").price(225).build(),
                Car.builder().make("Suzuki").model("Ciaz").colour("White").price(425).build(),
                Car.builder().make("Mazda").model("3").colour("Black").price(618).build(),
                Car.builder().make("Hyundai").model("i10").colour("Red").price(380).build(),
                Car.builder().make("Kia").model("Sedona").colour("White").price(1053).build(),
                Car.builder().make("Ford").model("Focus").colour("Silver").price(295).build(),
                Car.builder().make("Kia").model("Carens").colour("Black").price(383).build(),
                Car.builder().make("Hyundai").model("Accent").colour("Gold").price(525).build(),
                Car.builder().make("Kia").model("Morning").colour("White").price(300).build()
        );
        carRepository.saveAll(cars);
    }
}
