package com.zerobase.zerobasestudy.service.wifi;

import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;

import java.util.List;

public interface WifiService {
    int count();

    int save(List<Wifi> wifiList);

    void deleteAll();

    List<WifiDto.Response> getNearbyWifiDtoList(Double latitude, Double longitude, Integer limit);

}
