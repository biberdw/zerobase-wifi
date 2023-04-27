package com.zerobase.zerobasestudy.dto.wifi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.config.init.ObjectMapperSingleton;
import com.zerobase.zerobasestudy.config.init.OkHttpClientSingleton;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WifiApiDto {

    private static String API_URL = "http://openapi.seoul.go.kr:8088/564f4345626269623831506951736a/json/TbPublicWifiInfo/";


    @JsonProperty("TbPublicWifiInfo")
    TbPublicWifiInfo tbPublicWifiInfo;

    @Data
    public static class TbPublicWifiInfo {
        @JsonProperty("list_total_count")
        private int totalCount;
        @JsonProperty("RESULT")
        private Result result;
        private List<Row> row;

        @Data
        @NoArgsConstructor
        public static class Result {
            @JsonProperty("CODE")
            private String code;
            @JsonProperty("MESSAGE")
            private String message;
        }

        @Data
        @NoArgsConstructor
        public static class Row {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

            @JsonProperty("X_SWIFI_MGR_NO")
            private String control_number;

            @JsonProperty("X_SWIFI_WRDOFC")
            private String borough;

            @JsonProperty("X_SWIFI_MAIN_NM")
            private String name;

            @JsonProperty("X_SWIFI_ADRES1")
            private String address;

            @JsonProperty("X_SWIFI_ADRES2")
            private String detailedAddress;

            @JsonProperty("X_SWIFI_INSTL_FLOOR")
            private String floor;

            @JsonProperty("X_SWIFI_INSTL_TY")
            private String type;

            @JsonProperty("X_SWIFI_INSTL_MBY")
            private String agency;

            @JsonProperty("X_SWIFI_SVC_SE")
            private String serviceType;

            @JsonProperty("X_SWIFI_CMCWR")
            private String netType;

            @JsonProperty("X_SWIFI_CNSTC_YEAR")
            private String installationYear;

            @JsonProperty("X_SWIFI_INOUT_DOOR")
            private String inoutDoor;

            @JsonProperty("X_SWIFI_REMARS3")
            private String connEnv;

            @JsonProperty("LNT")
            private String latitude;

            @JsonProperty("LAT")
            private String longitude;

            @JsonProperty("WORK_DTTM")
            private String workDate;

            public Wifi toEntity(){
                return Wifi.builder()
                        .controlNumber(this.control_number)
                        .borough(this.borough)
                        .name(this.name)
                        .address(this.address)
                        .detailedAddress(this.detailedAddress)
                        .floor(this.floor)
                        .type(this.type)
                        .agency(this.agency)
                        .serviceType(this.serviceType)
                        .netType(this.netType)
                        .installationYear(this.installationYear)
                        .inoutDoor(this.inoutDoor)
                        .connEnv(this.connEnv)
                        .longitude(Double.parseDouble(this.longitude))
                        .latitude(Double.parseDouble(this.latitude))
                        .workDate(LocalDateTime.parse(this.workDate, formatter))
                        .build();
            }
        }

    }

    public List<Wifi> toEntities(){
        return this.tbPublicWifiInfo.row.stream()
                .map(TbPublicWifiInfo.Row::toEntity)
                .collect(Collectors.toList());
    }

    public static WifiApiDto fetchWifiApiDto(int start, int end){
        okhttp3.OkHttpClient client = OkHttpClientSingleton.getInstance();
        ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();

        StringBuilder builder = new StringBuilder();
        builder.append(API_URL)
                .append(start)
                .append("/")
                .append(end);

        Request request = new Request.Builder()
                .url(builder.toString())
                .build();
        try{
            Response response = client.newCall(request).execute();

            String json = response.body().string();
            return objectMapper.readValue(json, WifiApiDto.class);

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
