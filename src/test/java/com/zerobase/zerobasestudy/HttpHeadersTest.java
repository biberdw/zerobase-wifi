package com.zerobase.zerobasestudy;

import com.zerobase.zerobasestudy.util.HttpHeaders;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class HttpHeadersTest {

    @Test
    void 헤더테스트(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/x-www");

        Map<String, List<String>> headers = httpHeaders.getHeaders();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            List<String> value = entry.getValue();
            for (String s : value) {
                System.out.println(s);
            }
        }
    }
}
