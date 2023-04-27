package com.zerobase.zerobasestudy.config.init;


import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 제이슨 매핑을 위한 잭슨 라이브러리객체
 */
public class ObjectMapperSingleton {
    private static ObjectMapper instance;

    private ObjectMapperSingleton() {
    }

    public static synchronized ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapper();
        }
        return instance;
    }
}
