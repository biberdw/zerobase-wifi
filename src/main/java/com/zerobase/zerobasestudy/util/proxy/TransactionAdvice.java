package com.zerobase.zerobasestudy.util.proxy;

import com.zerobase.zerobasestudy.util.TransactionManager;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class TransactionAdvice implements InvocationHandler {
    private final Object target;
    private final TransactionManager transactionManager;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //트랜젝션 시작
        transactionManager.getTransaction();

        try{
            Object result = method.invoke(target, args);
            transactionManager.commit();
            return result;
        }catch (Exception e){
            transactionManager.rollback();
            throw new RuntimeException(e);
        }

    }
}
