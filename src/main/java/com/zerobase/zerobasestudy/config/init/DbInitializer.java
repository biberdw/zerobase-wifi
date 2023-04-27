package com.zerobase.zerobasestudy.config.init;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zerobase.zerobasestudy.util.exception.SqlException;

import javax.sql.DataSource;

import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.*;

public class DbInitializer {

    private static DataSource dataSource;

    private DbInitializer(){
    }

    /** db 정보 등록 및 싱글톤관리 */
    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            try {
                Class.forName(DRIVER);
                //히카리 라이브러리로 커넥션 풀 관리
                HikariConfig config = new HikariConfig();

                config.setJdbcUrl(URL);
                config.setUsername(USERNAME);
                config.setPassword(PASSWORD);
                config.setMaximumPoolSize(10);
                config.setDriverClassName(DRIVER);

                dataSource = new HikariDataSource(config);

            }catch (ClassNotFoundException cause){
                throw new SqlException("데이터베이스 연결 실패", cause);
            }
        }
        return dataSource;
    }

}
