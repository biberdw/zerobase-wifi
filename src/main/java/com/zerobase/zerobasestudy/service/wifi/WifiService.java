package com.zerobase.zerobasestudy.service.wifi;

import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;

import java.util.List;

public interface WifiService {

    /** 와이파이 배치 등록 */
    int save(List<Wifi> wifiList);


    /** 와이파이 전체 삭제 */
    void deleteAll();

    /** 와이파이 단건조회 */
    WifiDto.Response getWifiDto(Long id);

    /** 가장 가까운 와이파이 리스트 조회 */
    List<WifiDto.Response> getNearbyWifiDtoList(Double latitude, Double longitude, Integer limit);

    int count();

}
