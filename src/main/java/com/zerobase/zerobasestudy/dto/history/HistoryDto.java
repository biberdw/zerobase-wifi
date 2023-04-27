package com.zerobase.zerobasestudy.dto.history;

import com.zerobase.zerobasestudy.entity.history.History;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class HistoryDto {


    @Data
    @NoArgsConstructor
    public static class Response{
        private Long id; //pk
        private Double longitude; //경도
        private Double latitude; //위도
        private LocalDateTime created; //등록일

        public Response(History history) {
            this.id = history.getId();
            this.longitude = history.getLongitude();
            this.latitude = history.getLatitude();
            this.created = history.getCreated();
        }
    }


}
