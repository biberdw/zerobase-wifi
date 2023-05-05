package com.zerobase.zerobasestudy.dto.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.entity.history.History;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BookmarkDto {


    @Data
    public static class Request{
        private Long id;
        @JsonProperty
        private String name;
        private Integer sequenceNum;
        private LocalDateTime created;
        private LocalDateTime modified;


    }

    @Data
    @NoArgsConstructor
    public static class Response{
        private Long id;
        private String name;
        private Integer sequenceNum;
        private LocalDateTime created;
        private LocalDateTime modified;


        public Response(Bookmark bookmark) {
            this.id = bookmark.getId();
            this.name = bookmark.getName();
            this.sequenceNum = bookmark.getSequenceNum();
            this.created = bookmark.getCreated();
            this.modified = bookmark.getModified();
        }
    }
}
