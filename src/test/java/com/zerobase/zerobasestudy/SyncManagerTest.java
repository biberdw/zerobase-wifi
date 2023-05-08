package com.zerobase.zerobasestudy;

import com.zaxxer.hikari.HikariDataSource;
import com.zerobase.zerobasestudy.config.init.DbInitializer;
import com.zerobase.zerobasestudy.util.TransactionManager;
import com.zerobase.zerobasestudy.util.TransactionManagerJdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

import static com.zerobase.zerobasestudy.util.ConnectionSyncManager.*;

public class SyncManagerTest {

    @Test
    @DisplayName("처음 등록한 커넥션과 같은 객체가 조회하면 나와야 한다")
    void 싱크매니저_테스트() {
        Connection connection = getConnection();
        setSyncConnection(connection);

        Connection syncConnection = getConnection();
        System.out.println(connection);
        System.out.println(syncConnection);
        Assertions.assertEquals(connection, syncConnection);

    }

    @Test
    @DisplayName("쓰레드별로 다른 커넥션이 조회 돼야 한다")
    void 커넥션_조회(){

        AtomicReference<Connection> syncConn1 = new AtomicReference<>(null);
        AtomicReference<Connection> syncConn2 = new AtomicReference<>(null);

        Runnable runnable1 = () -> {
            Connection conn1 = getConnection();
            setSyncConnection(conn1);
            Connection syncConn = getConnection();
            System.out.println("쓰레드1 " + syncConn);
            syncConn1.set(syncConn);
        };

        Runnable runnable2 = () -> {
            Connection conn2 = getConnection();
            setSyncConnection(conn2);
            Connection syncConn = getConnection();
            System.out.println("쓰레드2 " + syncConn);
            syncConn2.set(syncConn);
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Connection connection1 = syncConn1.get();
        Connection connection2 = syncConn2.get();

        Assertions.assertNotEquals(connection1, connection2);
    }

    @Test
    @DisplayName("싱크커넥션을 등록하지 않으면 같은 쓰레드여도 다른 커넥션이 반환돼야 한다")
    void 커넥션_삭제(){
        Connection connection1 = getConnection();
        Connection connection2 = getConnection();
        System.out.println("connection1 = " + connection1);
        System.out.println("connection2 = " + connection2);
        Assertions.assertNotEquals(connection1, connection2);

    }

    @Test
    @DisplayName("싱크커넥션이 등록되지않은 커넥션을 릴리즈하면 풀에 반납")
    void release_test() throws SQLException {

        Connection connection = getConnection();
        release(connection);
        //싱크커넥션으로 등록하지않고 릴리즈하면 커넥션종료
        Assertions.assertTrue(connection.isClosed());

        Connection connection1 = getConnection();
        setSyncConnection(connection1);
        Connection syncConnection = getConnection();
        release(syncConnection);
        //싱크커넥션일시 릴리즈해도 커넥션 종료하지 않음
        Assertions.assertFalse(syncConnection.isClosed());
    }

    @Test
    @DisplayName("remove 를 하면 쓰레드로컬에서 커넥션 제거")
    void remove_test(){
        Connection connection = getConnection();
        setSyncConnection(connection);
        Connection syncConnection = getConnection();
        //같은쓰레드에서는 같은 커넥션이 조회돼야 한다
        Assertions.assertEquals(connection, syncConnection);

        remove();
        Connection findConnection = getConnection();
        System.out.println("findConnection = " + findConnection);
        //쓰레드로컬에서 커넥션을 제거했기 때문에 다른커넥션이 조회돼야 한다
        Assertions.assertNotEquals(syncConnection, findConnection);
    }


}