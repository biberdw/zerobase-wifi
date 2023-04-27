package com.zerobase.zerobasestudy.entity.wifi;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Wifi {

    private Long id;
    private String controlNumber;
    private String borough;
    private String name;
    private String address;
    private String detailedAddress;
    private String floor;
    private String type;
    private String agency;
    private String serviceType;
    private String netType;
    private String installationYear;
    private String inoutDoor;
    private String connEnv;
    private Double longitude;
    private Double latitude;
    private LocalDateTime workDate;

}
