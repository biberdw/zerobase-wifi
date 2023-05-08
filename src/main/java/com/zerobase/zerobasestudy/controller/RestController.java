package com.zerobase.zerobasestudy.controller;

import com.zerobase.zerobasestudy.util.HttpHeaders;
import com.zerobase.zerobasestudy.util.HttpStatus;
import com.zerobase.zerobasestudy.util.ResponseEntity;

import java.util.Map;

import static com.zerobase.zerobasestudy.util.HttpStatus.*;

public interface RestController {
    default ResponseEntity<Object> get(Map<String, String> paramMap){
        return ResponseEntity.isNotFound();
    }

    default ResponseEntity<Object> post(Map<String, String> paramMap){
        return ResponseEntity.isNotFound();
    }

    default ResponseEntity<Object> put(Map<String, String> paramMap){
        return ResponseEntity.isNotFound();
    }

    default ResponseEntity<Object> delete(Map<String, String> paramMap){
        return ResponseEntity.isNotFound();
    }
}
