package com.zerobase.zerobasestudy.config.init;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.*;

public class DbInitializer {

    private static DataSource dataSource;

    private DbInitializer(){
    }

    public static synchronized javax.sql.DataSource getDataSource() {
        if (dataSource == null) {
            try {
                Class.forName(DRIVER);
                HikariConfig config = new HikariConfig();

                config.setJdbcUrl(URL);
                config.setUsername(USERNAME);
                config.setPassword(PASSWORD);
                config.setMaximumPoolSize(10);
                config.setDriverClassName(DRIVER);

                dataSource = new HikariDataSource(config);

            }catch (ClassNotFoundException e){
                throw new IllegalStateException("데이터베이스 연결 실패");
            }
        }
        return dataSource;
    }

}
