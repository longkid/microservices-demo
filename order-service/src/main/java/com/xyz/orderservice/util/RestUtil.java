package com.xyz.orderservice.util;

import com.xyz.orderservice.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestUtil {
    private static final Logger log = LoggerFactory.getLogger(RestUtil.class);

    @Autowired
    private RestTemplate restTemplate;

    public Response doPost(String url, Object request) {
        try {
            return restTemplate.postForObject(url, request, Response.class);
        } catch (Exception e) {
            log.error("Fail to call POST API: url '{}', request '{}'", url, request, e);
            return Response.builder().success(false).msg(e.getMessage()).build();
        }
    }

    public Response doGet(String url) {
        try {
            return restTemplate.getForObject(url, Response.class);
        } catch (Exception e) {
            log.error("Fail to call GET API: url '{}'", url, e);
            return Response.builder().success(false).msg(e.getMessage()).build();
        }
    }
}
