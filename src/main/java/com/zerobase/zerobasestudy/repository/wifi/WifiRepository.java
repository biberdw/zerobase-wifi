package com.zerobase.zerobasestudy.repository.wifi;

import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;

import java.util.List;

public interface WifiRepository {
    int count();

    int save(List<Wifi> wifiList);

    void deleteAll();

    List<WifiDto.Response> findAllByLatAndLng(Double latitude, Double longitude, Integer limit);
}
