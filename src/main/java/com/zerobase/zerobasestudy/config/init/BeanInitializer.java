package com.zerobase.zerobasestudy.config.init;

import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BeanInitializer implements ServletContextListener {

    private HikariDataSource dataSource;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
//      서블릿 컨텍스트가 로드될때 데이터베이스 커넥션 연결
        dataSource = (HikariDataSource) DbInitializer.getDataSource();
    }

    /** 서버 내려갈때 커넥션 종료 */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //서버 내려갈때 커넥션 끊기 */
        if(dataSource != null){
            dataSource.close();;
        }
    }
}
