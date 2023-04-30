package com.zerobase.zerobasestudy.repository.wifi;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepositoryJdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.*;
import static org.junit.jupiter.api.Assertions.*;


class WifiRepositoryJdbcTest {

    WifiRepository repository;

    @BeforeEach
    void beforeEach(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setDriverClassName(DRIVER);
        HikariDataSource dataSource = new HikariDataSource(config);
        repository = new WifiRepositoryJdbc(dataSource);
    }

    @Test
    @DisplayName("카운트 테스트")
    void count(){
        int count = repository.count();
        assertEquals(2, count);
    }

    @Test
    @DisplayName("배치 삽입")
    void insertBatch(){
        // == 샘플 생성 == //
        Wifi wifi1 = Wifi.builder()
                .controlNumber("12345")
                .borough("Manhattan")
                .name("Central Park")
                .address("59th St to 110th St, from Central Park West to 5th Ave")
                .detailedAddress("")
                .floor("")
                .type("Free")
                .agency("NYC Parks")
                .serviceType("Limited Free")
                .netType("Public")
                .installationYear("2003")
                .inoutDoor("Outdoor")
                .connEnv("Parks")
                .longitude(73.9654)
                .latitude(40.7829)
                .workDate(LocalDateTime.now())
                .build();

        Wifi wifi2 = Wifi.builder()
                .controlNumber("12345")
                .borough("Manhattan")
                .name("Central Park")
                .address("59th St to 110th St, from Central Park West to 5th Ave")
                .detailedAddress("")
                .floor("")
                .type("Free")
                .agency("NYC Parks")
                .serviceType("Limited Free")
                .netType("Public")
                .installationYear("2003")
                .inoutDoor("Outdoor")
                .connEnv("Parks")
                .longitude(73.9654)
                .latitude(40.7829)
                .workDate(LocalDateTime.now())
                .build();
        // ==== //
        List<Wifi> list = new ArrayList<>();
        list.add(wifi1);
        list.add(wifi2);

        int count = repository.save(list);
        assertEquals(2, count);

    }

    @Test
    @DisplayName("가까운 와이파이 List 조회")
    void findAllByLatAndLng(){
        Double lnt = 127.041564; //경도
        Double lat = 37.502982; // 위도
        //경도 126.990524
        //위도 37.498795
        List<WifiDto.Response> findList = repository.findAllByLatAndLng(lat, lnt, 20);

        for (WifiDto.Response wifi : findList) {
            System.out.println("distance" + wifi.getDistance());
            System.out.println("관리번호= " + wifi.getControlNumber());
            System.out.println("자치구= " + wifi.getBorough());
            System.out.println("와이파이명= " + wifi.getName());
            System.out.println("도로명주소= " + wifi.getAddress());
            System.out.println("상세주소= " + wifi.getDetailedAddress());
            System.out.println("설치위치(층)= " + wifi.getFloor());
            System.out.println("설치유형= " + wifi.getType());
            System.out.println("설치기관= " + wifi.getAgency());
            System.out.println("서비스구분= " + wifi.getServiceType());
            System.out.println("망종류= " + wifi.getNetType());
            System.out.println("설치년도= " + wifi.getInstallationYear());
            System.out.println("실내외구분= " + wifi.getInoutDoor());
            System.out.println("접속환경= " + wifi.getConnEnv());
            System.out.println("경도= " + wifi.getLongitude());
            System.out.println("위도= " + wifi.getLatitude());
            System.out.println("작업일자= " + wifi.getWorkDate());
            System.out.println("==============================");
        }

        assertEquals(20, findList.size());
    }

    @Test
    @DisplayName("단건조회")
    void 단건조회(){
        Optional<Wifi> find = repository.findById(24082L);
        Wifi wifi = find.get();
        Assertions.assertEquals(24082L, wifi.getId());

    }

}