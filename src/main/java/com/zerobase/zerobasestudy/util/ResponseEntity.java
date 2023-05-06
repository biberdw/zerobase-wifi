package com.zerobase.zerobasestudy.util;

import com.zerobase.zerobasestudy.util.HttpStatus;
import lombok.Builder;

@Builder
public class ResponseEntity<T> {
    private final HttpHeaders httpHeaders;
    private final T body;
    private final HttpStatus httpStatus;


    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public T getBody() {
        return body;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
