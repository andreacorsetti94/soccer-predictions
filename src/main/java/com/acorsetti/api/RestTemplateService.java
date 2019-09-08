package com.acorsetti.api;

import org.springframework.http.ResponseEntity;

public interface RestTemplateService<T> {
    ResponseEntity<T> makeGetRestCall(String url,Class<T> tClass);
}
