package com.zerobase.zerobasestudy.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerJdbc implements TransactionManager{

    @Override
    public void getTransaction() {
        try {
            Connection connection = ConnectionSyncManager.getConnection();
            ConnectionSyncManager.setSyncConnection(connection);
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commit(){

        Connection connection = null;
        try {
            connection = ConnectionSyncManager.getConnection();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                    ConnectionSyncManager.remove();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void rollback() {

        Connection connection = null;

        try {
            connection = ConnectionSyncManager.getConnection();
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                    ConnectionSyncManager.remove();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
