package com.zerobase.zerobasestudy.service.wifi;

import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.entity.bookmark.WifiBookmark;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.repository.bookmark.WifiBookmarkRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WifiServiceImpl implements WifiService{

    private final WifiRepository wifiRepository;
    private final WifiBookmarkRepository wifiBookmarkRepository;


    /** 와이파이 배치 등록 */
    public int save(List<Wifi> wifiList) {
        return wifiRepository.save(wifiList);
    }

    /** 가장 가까운 와이파이 리스트 조회 */
    public List<WifiDto.Response> getNearbyWifiDtoList(Double latitude, Double longitude, Integer limit) {
        return wifiRepository.findAllByLatAndLng(latitude, longitude, limit);
    }
    /** 와이파이 전체 삭제 */
    public void deleteAll() {
        //차일드테이블 삭제
        wifiBookmarkRepository.deleteAll();
        wifiRepository.deleteAll();
    }

    /** 와이파이 단건 조회 */
    public WifiDto.Response getWifiDto(Long id) {
        Wifi wifi = wifiRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 와이파이 입니다. id = " + id));
        return new WifiDto.Response(wifi);
    }


    public int count() {
        return wifiRepository.count();
    }

}
