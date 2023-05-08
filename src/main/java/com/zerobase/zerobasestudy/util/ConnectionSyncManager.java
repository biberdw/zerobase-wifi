package com.zerobase.zerobasestudy.util;

import com.zerobase.zerobasestudy.config.init.DbInitializer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSyncManager {

    private static ThreadLocal<Connection> connections = new ThreadLocal<>();
    private static DataSource dataSource = DbInitializer.getDataSource();


    public static Connection getConnection(){
        if(connections.get() == null){
            try {
                return dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        Connection connection = connections.get();
        return connection;
    }

    public static void setSyncConnection(Connection conn){
        connections.set(conn);
    }

    public static void release(Connection conn) {
        try {
            if(connections.get() == null){
                System.out.println("여기 절대 걸리면 안돼");
                conn.close();;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void remove(){
        connections.remove();
    }


}
