package com.zerobase.zerobasestudy.util;

public enum HttpStatus {
    OK(200),
    BAD_REQUEST(400),
    NOTFOUND(404),
    CONFLICT(409);

    private final int value;

    HttpStatus(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
