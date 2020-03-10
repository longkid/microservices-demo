package com.xyz.carcatalogservice.util;

import com.xyz.carcatalogservice.dto.Response;
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
            log.error("Fail to call API: url '{}', request '{}'", url, request, e);
            return Response.builder().success(false).msg(e.getMessage()).build();
        }
    }
}
