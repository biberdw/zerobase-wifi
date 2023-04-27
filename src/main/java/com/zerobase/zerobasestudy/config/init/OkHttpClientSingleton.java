package com.zerobase.zerobasestudy.config.init;

import okhttp3.OkHttpClient;

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
