package com.zerobase.zerobasestudy.config.init;

import com.zerobase.zerobasestudy.util.TransactionManager;
import com.zerobase.zerobasestudy.util.TransactionManagerJdbc;
import okhttp3.OkHttpClient;


public class TransactionManagerSingleton {
    private static TransactionManager instance;

    private TransactionManagerSingleton(){

    }

    public static synchronized TransactionManager getInstance(){
        if (instance == null) {
            instance = new TransactionManagerJdbc();
        }
        return instance;
    }
}
