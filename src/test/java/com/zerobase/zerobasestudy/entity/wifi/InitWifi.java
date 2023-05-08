package com.zerobase.zerobasestudy.entity.wifi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InitWifi {

    public static Wifi getWifi(){
        return  Wifi.builder().id(1L)
                .controlNumber("7890") // Wi-Fi 제어 번호
                .borough("Manhattan") // Wi-Fi 소속 자치구
                .name("Example Wi-Fi") // Wi-Fi 이름
                .address("123 Example Street") // Wi-Fi 주소
                .detailedAddress("Apartment 4B") // Wi-Fi 상세 주소
                .floor("2nd Floor") // Wi-Fi 층수
                .type("Public") // Wi-Fi 유형
                .agency("Example Agency") // Wi-Fi 운영 기관
                .serviceType("Free") // Wi-Fi 서비스 유형
                .netType("Wi-Fi") // 네트워크 유형
                .inoutDoor("Indoor") // 실내 또는 실외 설치 여부
                .connEnv("Public") // 연결 환경
                .longitude(1.1) // Wi-Fi 위치의 경도
                .latitude(1.1) // Wi-Fi 위치의 위도
                .workDate(LocalDateTime.now()) // Wi-Fi 설치 또는 운영일자
                .build();
    }

    public static List<Wifi> getWifiList(){
        List<Wifi> wifiList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Wifi wifi = Wifi.builder()
                    .id((long) i)
                    .controlNumber("" + i) // Wi-Fi 제어 번호
                    .borough("Manhattan " + i) // Wi-Fi 소속 자치구
                    .name("Example Wi-Fi " + i) // Wi-Fi 이름
                    .address("123 Example Street " + i) // Wi-Fi 주소
                    .detailedAddress("Apartment 4B " + i) // Wi-Fi 상세 주소
                    .floor("2nd Floor " + i) // Wi-Fi 층수
                    .type("Public " + i) // Wi-Fi 유형
                    .agency("Example Agency " + i) // Wi-Fi 운영 기관
                    .serviceType("Free " + i) // Wi-Fi 서비스 유형
                    .netType("Wi-Fi "  + i) // 네트워크 유형
                    .inoutDoor("Indoor "  + i) // 실내 또는 실외 설치 여부
                    .connEnv("Public "  + i) // 연결 환경
                    .longitude(1.1 + i) // Wi-Fi 위치의 경도
                    .latitude(1.1 + i) // Wi-Fi 위치의 위도
                    .workDate(LocalDateTime.now()) // Wi-Fi 설치 또는 운영일자
                    .build();
            wifiList.add(wifi);
        }
        return wifiList;
    }
}
