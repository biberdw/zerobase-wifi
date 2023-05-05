package com.zerobase.zerobasestudy.util;

import com.zerobase.zerobasestudy.util.HttpStatus;

public class ResponseEntity<T> {
    private final HttpHeaders httpHeaders;
    private final T body;
    private final HttpStatus httpStatus;

    public ResponseEntity(HttpHeaders httpHeaders, T body, HttpStatus httpStatus) {
        this.httpHeaders = httpHeaders;
        this.body = body;
        this.httpStatus = httpStatus;
    }

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
