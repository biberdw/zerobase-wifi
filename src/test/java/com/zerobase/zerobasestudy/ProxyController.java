package com.zerobase.zerobasestudy;

import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.service.wifi.WifiService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProxyController {
    private final WifiService wifiService;

    public void delete(){
        wifiService.deleteAll();
    }

    public void select(){
        WifiDto.Response wifiDto = wifiService.getWifiDto(1L);
        System.out.println("셀렉트호출!!");
        if(wifiDto == null){
            System.out.println("널");
        }else {
            System.out.println(wifiDto.getAgency());
            System.out.println(wifiDto.getAgency());
        }

    }
}
