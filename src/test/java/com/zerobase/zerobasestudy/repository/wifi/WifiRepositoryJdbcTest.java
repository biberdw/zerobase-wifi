package com.zerobase.zerobasestudy.repository.wifi;

import com.zerobase.zerobasestudy.config.init.TransactionManagerSingleton;
import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.util.TransactionManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.zerobase.zerobasestudy.entity.wifi.InitWifi.*;
import static org.junit.jupiter.api.Assertions.*;

public class WifiRepositoryJdbcTest {

    WifiRepository wifiRepository;
    TransactionManager transactionManager;

    @BeforeEach
    void beforeEach() {
        transactionManager = TransactionManagerSingleton.getInstance();;
        wifiRepository = new WifiRepositoryJdbc();
        transactionManager.getTransaction();
    }

    @AfterEach
    void afterEach(){
        transactionManager.rollback();
    }

    @Test
    void count(){
        Wifi wifi = getWifi();
        List<Wifi> wifiList = new ArrayList<>();
        wifiList.add(wifi);
        wifiRepository.save(wifiList);

        int count = wifiRepository.count();

        assertEquals(1, count);
    }


    @Test
    void deleteAll(){
        List<Wifi> wifiList = getWifiList();
        wifiRepository.save(wifiList);

        wifiRepository.deleteAll();
        int result = wifiRepository.count();

        assertEquals(0 , result);
    }

    @Test
    void findAllByLatAndLng(){
        List<Wifi> wifiList = getWifiList();
        wifiRepository.save(wifiList);
        Wifi wifi = wifiList.get(4);


        List<WifiDto.Response> responses =
                wifiRepository.findAllByLatAndLng(wifi.getLatitude(), wifi.getLongitude(), 20);


        //나 자신이 제일 가까운 데이터로 조회 돼야 한다
        WifiDto.Response response = responses.get(0);
        assertEquals(wifi.getId(), response.getId());

    }

    @Test
    void findById(){
        Wifi wifi = getWifi();
        List<Wifi> wifiList = new ArrayList<>();
        wifiList.add(wifi);

        wifiRepository.save(wifiList);

        Wifi findWifi = wifiRepository.findById(wifi.getId()).orElseGet(null);


        assertEquals(wifi, findWifi);
    }


}
