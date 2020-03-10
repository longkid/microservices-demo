package com.xyz.orderservice.entity;

import com.xyz.orderservice.dto.CarOrderDto;
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
public class CarOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long carId;
    private String customerName;
    private String customerPhone;

    public CarOrder(CarOrderDto dto) {
        setCarId(dto.getCarId());
        setCustomerName(dto.getCustomerName());
        setCustomerPhone(dto.getCustomerPhone());
    }
}
