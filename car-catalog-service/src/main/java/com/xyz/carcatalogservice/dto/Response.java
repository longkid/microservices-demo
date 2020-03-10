package com.xyz.carcatalogservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private Object result;
    private boolean success;
    private String msg;
}
