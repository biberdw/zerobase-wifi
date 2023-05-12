package com.zerobase.zerobasestudy.config.init;

import com.zerobase.zerobasestudy.util.TransactionManager;
import com.zerobase.zerobasestudy.util.TransactionManagerJdbc;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;


@UtilityClass
public class TransactionManagerSingleton {
    private static TransactionManager instance;

    public static synchronized TransactionManager getInstance(){
        if (instance == null) {
            instance = new TransactionManagerJdbc();
        }
        return instance;
    }
}
