package com.xyz.carcatalogservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarOrderDto {
    private long carId;
    private String customerName;
    private String customerPhone;
}
