package com.zerobase.zerobasestudy.service.wifi;

import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;

import java.util.List;

public class WifiServiceImpl implements WifiService{

    private final WifiRepository wifiRepository;

    public WifiServiceImpl(WifiRepository wifiRepository) {
        this.wifiRepository = wifiRepository;
    }

    public int count() {
        return wifiRepository.count();
    }


    public int save(List<Wifi> wifiList) {
        return wifiRepository.save(wifiList);
    }

    public void deleteAll() {
        wifiRepository.deleteAll();
    }


    public List<WifiDto.Response> getNearbyWifiDtoList(Double latitude, Double longitude, Integer limit) {
        return wifiRepository.findAllByLatAndLng(latitude, longitude, limit);
    }


}
