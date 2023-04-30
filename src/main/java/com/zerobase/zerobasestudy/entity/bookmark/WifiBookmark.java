package com.zerobase.zerobasestudy.entity.bookmark;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WifiBookmark {

    private Long id;
    private String wifiName;
    private String bookmarkName;
    private Long bookmarkId;
    private Long wifiId;

}
