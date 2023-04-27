package com.zerobase.zerobasestudy.config.init;

import okhttp3.OkHttpClient;

/**
 * 오픈 api 의 요청을 위한 라이브러리 객체
 */
public class OkHttpClientSingleton {
    private static OkHttpClient instance;

    private OkHttpClientSingleton() {
    }

    public static synchronized okhttp3.OkHttpClient getInstance() {
        if (instance == null) {
            instance = new OkHttpClient();
        }
        return instance;
    }
}
