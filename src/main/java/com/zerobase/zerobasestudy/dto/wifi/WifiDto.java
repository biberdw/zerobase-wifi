package com.zerobase.zerobasestudy.dto.wifi;

import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import lombok.*;

public class WifiDto {



    @Data
    @NoArgsConstructor
    public static class Response {

        private String distance;
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
        private String workDate;

        public Response(Wifi wifi) {

            this.controlNumber = wifi.getControlNumber();
            this.borough = wifi.getBorough();
            this.name = wifi.getName();
            this.address = wifi.getAddress();
            this.detailedAddress = wifi.getDetailedAddress();
            this.floor = wifi.getFloor();
            this.type = wifi.getType();
            this.agency = wifi.getAgency();
            this.serviceType = wifi.getServiceType();
            this.netType = wifi.getNetType();
            this.installationYear = wifi.getInstallationYear();
            this.inoutDoor = wifi.getInoutDoor();
            this.connEnv = wifi.getConnEnv();
            this.longitude = wifi.getLongitude();
            this.latitude = wifi.getLatitude();
            this.workDate = wifi.getWorkDate().toString();
        }
    }
}
