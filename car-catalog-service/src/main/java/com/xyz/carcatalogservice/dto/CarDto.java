package com.xyz.carcatalogservice.dto;

import com.xyz.carcatalogservice.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String make;
    private String model;
    private String colour;
    private long price;
    private long viewCount;
    private boolean ordered;

    public CarDto(Car car) {
        setMake(car.getMake());
        setModel(car.getModel());
        setColour(car.getColour());
        setPrice(car.getPrice());
        setViewCount(car.getViewCount());
        setOrdered(car.isOrdered());
    }
}
