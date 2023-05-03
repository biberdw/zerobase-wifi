package com.zerobase.zerobasestudy.util.proxy;

import com.zerobase.zerobasestudy.util.TxManagerJdbc;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.UUID;

@RequiredArgsConstructor
public class TxHandler implements InvocationHandler {

    private final Object target;
    private final TxManagerJdbc txManager;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        txManager.setConnection();
        Connection connection = txManager.getConnection();

        try {
            connection.setAutoCommit(false);

            Object result = method.invoke(target, args);

            connection.commit();
            return result;
        } catch (Exception e){
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    txManager.remove();
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("database error");
                }
            }
        }
    }
}
