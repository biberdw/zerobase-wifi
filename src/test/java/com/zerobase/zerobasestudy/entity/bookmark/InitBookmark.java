package com.zerobase.zerobasestudy.entity.bookmark;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InitBookmark {

    public static Bookmark getBookmark(){
        return Bookmark.builder()
                .id(1L)
                .name("내차")
                .sequenceNum(1)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();
    }

    public static List<Bookmark> getBookmarks(){
        List<Bookmark> bookmarks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Bookmark bookmark = Bookmark.builder()
                    .id(((long) i))
                    .sequenceNum(i)
                    .name("이름" + i)
                    .created(LocalDateTime.now())
                    .modified(LocalDateTime.now())
                    .build();
            bookmarks.add(bookmark);
        }
        return bookmarks;
    }
}
