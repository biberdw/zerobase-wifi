package com.zerobase.zerobasestudy;

import com.zerobase.zerobasestudy.util.ConnectionSyncManager;
import com.zerobase.zerobasestudy.util.TransactionManager;
import com.zerobase.zerobasestudy.util.TransactionManagerJdbc;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.TestWatcher;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerTest {

    TransactionManager transactionManager;

    @BeforeEach
    void beforeEach(){
        transactionManager = new TransactionManagerJdbc();
    }


    @AfterEach
    void afterEach(TestInfo testInfo){
        String currentTestName = testInfo.getTestMethod().get().getName();
        if (currentTestName.equals("커밋")){
            return;
        }
        transactionManager.rollback();
    }


    @Test
    @DisplayName("트랜젝션을 시작하면 autoCommit 이 false 가 돼야 한다.")
    void 트랜젝션_시작() throws SQLException {
        transactionManager.getTransaction();
        Connection connection = ConnectionSyncManager.getConnection();

        Assertions.assertFalse(connection.getAutoCommit());
    }

    @Test
    @DisplayName("커밋")
    void 커밋(){
        transactionManager.getTransaction();
        transactionManager.commit();

    }



}
