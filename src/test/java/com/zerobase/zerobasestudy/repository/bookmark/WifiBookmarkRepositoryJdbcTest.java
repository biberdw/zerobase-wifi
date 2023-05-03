package com.zerobase.zerobasestudy.repository.bookmark;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.entity.bookmark.WifiBookmark;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepositoryJdbc;
import com.zerobase.zerobasestudy.util.Sort;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.*;
import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.DRIVER;
import static org.junit.jupiter.api.Assertions.*;

class WifiBookmarkRepositoryJdbcTest {

    WifiBookmarkRepository wifiBookmarkRepository;
    WifiRepository wifiRepository;
    BookmarkRepository bookmarkRepository;

    @BeforeEach
    void beforeEach() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setDriverClassName(DRIVER);
        HikariDataSource dataSource = new HikariDataSource(config);
        wifiBookmarkRepository = new WifiBookmarkRepositoryJdbc(dataSource);
        wifiRepository = new WifiRepositoryJdbc(dataSource);
        bookmarkRepository = new BookmarkRepositoryJdbc(dataSource);
    }



    @Test
    @DisplayName("와이파이북마크 등록")
    void 와이파이북마크_등록(){

        Bookmark bookmark = bookmarkRepository.findById(3L).get();
        Wifi wifi = wifiRepository.findById(2L).get();

        System.out.println(bookmark.getName());

        WifiBookmark wifiBookmark = WifiBookmark.builder()
                .wifiName(wifi.getName())
                .bookmarkName(bookmark.getName())
                .bookmarkId(bookmark.getId())
                .wifiId(wifi.getId())
                .build();

        wifiBookmarkRepository.save(wifiBookmark);

    }

    @Test
    @DisplayName("와이파이북마크 전체조회")
    void 와이파이북마크_전체조회(){
        Sort sort = new Sort("wifi_bookmark_id", Sort.Direction.ASC);
        List<WifiBookmark> wifiBookmarks = wifiBookmarkRepository.findAll(null, sort);

        for (WifiBookmark wifiBookmark : wifiBookmarks) {
            System.out.println(wifiBookmark.getWifiId());
            System.out.println(wifiBookmark.getBookmarkId());
            System.out.println(wifiBookmark.getBookmarkName());
            System.out.println(wifiBookmark.getWifiName());
            System.out.println(wifiBookmark.getCreated());
        }

        Assertions.assertEquals(6, wifiBookmarks.size() );
    }

}