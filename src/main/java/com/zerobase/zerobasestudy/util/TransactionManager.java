package com.zerobase.zerobasestudy.util;

import java.sql.SQLException;

public interface TransactionManager {
    void commit();
    void rollback();
    void getTransaction();
}
