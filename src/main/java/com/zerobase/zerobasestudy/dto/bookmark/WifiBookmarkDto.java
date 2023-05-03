package com.zerobase.zerobasestudy.dto.bookmark;

import com.zerobase.zerobasestudy.entity.bookmark.WifiBookmark;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class WifiBookmarkDto {

    @Data
    @NoArgsConstructor
    public static class Response{
        private Long id;
        private String wifiName;
        private String bookmarkName;
        private Long bookmarkId;
        private Long wifiId;
        private LocalDateTime created;

        public Response(WifiBookmark wifiBookmark, Long sequence) {

            this.id = sequence;
            this.wifiName = wifiBookmark.getWifiName();
            this.bookmarkName = wifiBookmark.getBookmarkName();
            this.bookmarkId = wifiBookmark.getBookmarkId();
            this.wifiId = wifiBookmark.getWifiId();
            this.created = wifiBookmark.getCreated();
        }
    }
}
