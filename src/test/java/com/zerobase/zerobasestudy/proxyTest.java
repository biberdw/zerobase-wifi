package com.zerobase.zerobasestudy;

import com.zerobase.zerobasestudy.config.init.DbInitializer;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepositoryJdbcV1;
import com.zerobase.zerobasestudy.service.Strs;
import com.zerobase.zerobasestudy.service.wifi.WifiService;
import com.zerobase.zerobasestudy.service.wifi.WifiServiceImplV1;
import com.zerobase.zerobasestudy.util.TxManagerJdbc;
import com.zerobase.zerobasestudy.util.proxy.TxHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;

public class proxyTest {

    @Test
    @DisplayName("트랜젝션 테스트")
    void proxy_test() throws InterruptedException {
        DataSource dataSource = DbInitializer.getDataSource();
        TxManagerJdbc txManagerJdbc = new TxManagerJdbc(dataSource);


        WifiRepository wifiRepository = new WifiRepositoryJdbcV1(txManagerJdbc);
        WifiService wifiService = new WifiServiceImplV1(wifiRepository);

        WifiService proxy = (WifiService) Proxy.newProxyInstance(WifiService.class.getClassLoader(),
                new Class[]{WifiService.class},
                 new TxHandler(wifiService, txManagerJdbc));

        ProxyController controller = new ProxyController(proxy);

        Runnable runnable1 = () -> controller.delete();
        Runnable runnable2 = () -> controller.select();

        new Thread(runnable1).start();
        Thread.sleep(2000);
        new Thread(runnable2).start();

        Thread.sleep(13000);



    }

    @Test
    @DisplayName("쓰레드로컬 반환값 확인")
    void threadLocal(){
        ThreadLocal<Strs> local = new ThreadLocal<>();
        local.set(new Strs("asd"));
        Strs strs = local.get();
        System.out.println("쓰레드1"  + strs.hashCode());

        Runnable runnable = () -> {
            Strs strs1 = local.get();
            System.out.println("쓰레드2" + strs.hashCode());
        };

        runnable.run();
    }
}
