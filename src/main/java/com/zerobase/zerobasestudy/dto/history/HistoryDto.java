package com.zerobase.zerobasestudy.dto.history;

import com.zerobase.zerobasestudy.entity.history.History;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class HistoryDto {


    @Data
    @NoArgsConstructor
    public static class Response{
        private Long id;
        private Double longitude;
        private Double latitude;
        private LocalDateTime created;

        public Response(History history) {
            this.id = history.getId();
            this.longitude = history.getLongitude();
            this.latitude = history.getLatitude();
            this.created = history.getCreated();
        }
    }


}
