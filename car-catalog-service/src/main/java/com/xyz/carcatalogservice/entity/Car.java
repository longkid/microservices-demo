package com.xyz.carcatalogservice.entity;

import com.xyz.carcatalogservice.dto.CarDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String make;
    private String model;
    private String colour;
    private long price;
    private long viewCount;
    private boolean ordered;

    public Car(CarDto car) {
        setMake(car.getMake());
        setModel(car.getModel());
        setColour(car.getColour());
        setPrice(car.getPrice());
        setViewCount(car.getViewCount());
        setOrdered(car.isOrdered());
    }
}
