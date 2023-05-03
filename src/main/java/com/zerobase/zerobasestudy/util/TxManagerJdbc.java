package com.zerobase.zerobasestudy.util;

import com.zerobase.zerobasestudy.util.exception.SqlException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TxManagerJdbc {

    ThreadLocal<Connection> connections;
    DataSource dataSource;

    public TxManagerJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
        connections = new ThreadLocal<>();
    }

    public void setConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        connections.set(conn);
    }

    public Connection getConnection() throws SQLException {

        if(connections.get() == null){
            return dataSource.getConnection();
        }

        Connection connection = connections.get();
        System.out.println("TXMANAGER= " + connection.hashCode());
        return connection;
    }

    public void release(Connection conn) {
        try {
            if(!connections.get().equals(conn) || connections.get() == null ){
                System.out.println("여기 절대 걸리면 안돼");
                conn.close();;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(){
        connections.remove();
    }

}
