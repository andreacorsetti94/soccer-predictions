package com.acorsetti.api.impl;

import com.acorsetti.api.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateServiceImpl<T> implements RestTemplateService<T> {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntity<String> httpEntity;

    @Override
    public ResponseEntity<T> makeGetRestCall(String url, Class<T> tClass) {
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, tClass);
    }
}
