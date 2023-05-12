package com.zerobase.zerobasestudy.config.init;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

/**
 * 제이슨 매핑을 위한 잭슨 라이브러리객체
 */
@UtilityClass
public class ObjectMapperSingleton {
    private static ObjectMapper instance;

    public static synchronized ObjectMapper getInstance() {
        if (instance == null) {
            instance = new ObjectMapper();
        }
        return instance;
    }
}
