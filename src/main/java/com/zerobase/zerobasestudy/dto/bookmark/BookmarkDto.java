package com.zerobase.zerobasestudy.dto.bookmark;

import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.entity.history.History;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BookmarkDto {


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
