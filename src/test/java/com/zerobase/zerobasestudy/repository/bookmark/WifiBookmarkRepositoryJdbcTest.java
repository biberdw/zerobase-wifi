package com.zerobase.zerobasestudy.repository.bookmark;

import com.zerobase.zerobasestudy.config.init.TransactionManagerSingleton;
import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.entity.bookmark.WifiBookmark;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepositoryJdbc;
import com.zerobase.zerobasestudy.util.TransactionManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

import static com.zerobase.zerobasestudy.entity.bookmark.InitBookmark.*;
import static com.zerobase.zerobasestudy.entity.bookmark.InitWifiBookmark.*;
import static com.zerobase.zerobasestudy.entity.wifi.InitWifi.*;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

public class WifiBookmarkRepositoryJdbcTest {

    TransactionManager transactionManager;
    WifiBookmarkRepository wifiBookmarkRepository;
    BookmarkRepository bookmarkRepository;
    WifiRepository wifiRepository;

    @BeforeEach
    void beforeEach(){
        transactionManager = TransactionManagerSingleton.getInstance();
        wifiBookmarkRepository = new WifiBookmarkRepositoryJdbc();
        bookmarkRepository = new BookmarkRepositoryJdbc();
        wifiRepository = new WifiRepositoryJdbc();
        transactionManager.getTransaction();
    }

    @AfterEach
    void afterEach(){
        transactionManager.rollback();
    }

    /** 와이파이_즐겨찾기 등록 */
    @Test
    void save(){
        Bookmark bookmark = getBookmark();
        Wifi wifi = getWifi();
        List<Wifi> wifiList = new ArrayList<>();
        wifiList.add(wifi);
        wifiRepository.save(wifiList);
        bookmarkRepository.save(bookmark);
        Bookmark findBookmark = bookmarkRepository.findById(bookmark.getId()).orElseGet(null);
        Wifi findWifi = wifiRepository.findById(wifi.getId()).orElseGet(null);
        WifiBookmark wifiBookmark = getWifiBookmark(findWifi, findBookmark);

        //when
        int result = wifiBookmarkRepository.save(wifiBookmark);

        //then
        List<WifiBookmark> findWifiBookmarks = wifiBookmarkRepository.findAll(null, null);
        WifiBookmark findWifiBookmark = findWifiBookmarks.get(0);
        assertEquals(wifiBookmark, findWifiBookmark);


    }

    /** 와이파이_즐겨찾기 전체 조회 */
    @Test
    void findAll(){

        List<Bookmark> bookmarks = getBookmarks();
        bookmarks.forEach(bookmarkRepository::save);

        List<Wifi> wifiList = getWifiList();
        wifiRepository.save(wifiList);

        List<Wifi> findWifiList = LongStream.range(1, wifiList.size() + 1)
                .boxed()
                .map(aLong -> wifiRepository.findById(aLong).orElseGet(null))
                .collect(toList());
        List<Bookmark> findBookmarks = LongStream.range(1, bookmarks.size() + 1)
                .boxed()
                .map(aLong -> bookmarkRepository.findById(aLong).orElseGet(null))
                .collect(toList());

        List<WifiBookmark> wifiBookmarks = getWifiBookmarks(findWifiList, findBookmarks);
        wifiBookmarks.stream().forEach(wifiBookmarkRepository::save);


        //when
        List<WifiBookmark> findWifiBookmarks = wifiBookmarkRepository.findAll(null, null);

        //then
        assertEquals(wifiBookmarks.size(), findWifiBookmarks.size());
    }

    /** 와이파이_즐겨찾기 삭제 */
    @Test
    void deleteById(){

        Bookmark bookmark = getBookmark();
        bookmarkRepository.save(bookmark);
        Wifi wifi = getWifi();
        List<Wifi> wifiList = Collections.singletonList(wifi);
        wifiRepository.save(wifiList);
        Bookmark findBookmark = bookmarkRepository.findById(bookmark.getId()).orElseGet(null);
        Wifi findWifi = wifiRepository.findById(wifi.getId()).orElseGet(null);

        WifiBookmark wifiBookmark = getWifiBookmark(findWifi, findBookmark);
        wifiBookmarkRepository.save(wifiBookmark);

        //when
        wifiBookmarkRepository.deleteById(wifiBookmark.getWifiId(), wifiBookmark.getBookmarkId());

        //then
        List<WifiBookmark> wifiBookmarks = wifiBookmarkRepository.findAll(null, null);

        assertEquals(0, wifiBookmarks.size());


    }



}
