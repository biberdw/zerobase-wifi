package com.zerobase.zerobasestudy.util;

import com.zerobase.zerobasestudy.util.HttpStatus;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static com.zerobase.zerobasestudy.util.HttpStatus.*;

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

    public static ResponseEntity isOk(){
        return ResponseEntity.builder()
                .httpStatus(OK)
                .build();
    }

    public static ResponseEntity isNotFound(){
        return ResponseEntity.builder()
                .httpStatus(NOTFOUND)
                .build();
    }
}
