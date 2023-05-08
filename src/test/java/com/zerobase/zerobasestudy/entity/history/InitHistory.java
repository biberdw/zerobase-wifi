package com.zerobase.zerobasestudy.entity.history;

import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InitHistory {

    public static History getHistory(){
        return History.builder()
                .id(1L)
                .longitude(13.11)
                .latitude(14.11)
                .created(LocalDateTime.now())
                .build();
    }

    public static List<History> getHistories(){
        List<History> histories = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            History history = History.builder()
                    .id((long) i)
                    .longitude(13.11 + i)
                    .latitude(14.11 + i)
                    .created(LocalDateTime.now())
                    .build();
            histories.add(history);
        }
        return histories;
    }
}
