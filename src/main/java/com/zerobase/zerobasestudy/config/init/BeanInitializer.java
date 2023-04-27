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
        dataSource = (HikariDataSource) DbInitializer.getDataSource();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if(dataSource != null){
            dataSource.close();;
        }
    }
}
