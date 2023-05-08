package com.zerobase.zerobasestudy.entity.bookmark;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode
public class WifiBookmark {

    private String wifiName;
    private String bookmarkName;
    private Long bookmarkId;
    private Long wifiId;
    private LocalDateTime created;

}
