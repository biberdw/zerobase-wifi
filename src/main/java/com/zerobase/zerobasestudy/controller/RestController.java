package com.zerobase.zerobasestudy.controller;

import com.zerobase.zerobasestudy.util.HttpHeaders;
import com.zerobase.zerobasestudy.util.HttpStatus;
import com.zerobase.zerobasestudy.util.ResponseEntity;

import java.util.Map;

public interface RestController {
    default ResponseEntity<Object> get(Map<String, String> paramMap){
        return new ResponseEntity<>(new HttpHeaders(), null, HttpStatus.OK);
    }

    default ResponseEntity<Object> post(Map<String, String> paramMap){
        return new ResponseEntity<>(new HttpHeaders(), null, HttpStatus.OK);
    }

    default ResponseEntity<Object> put(Map<String, String> paramMap){
        return new ResponseEntity<>(new HttpHeaders(), null, HttpStatus.OK);
    }

    default ResponseEntity<Object> delete(Map<String, String> paramMap){
        return new ResponseEntity<>(new HttpHeaders(), null, HttpStatus.OK);
    }
}
