package com.zerobase.zerobasestudy.repository.bookmark;

import com.zerobase.zerobasestudy.config.init.TransactionManagerSingleton;
import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.entity.bookmark.WifiBookmark;
import com.zerobase.zerobasestudy.entity.wifi.InitWifi;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepositoryJdbc;
import com.zerobase.zerobasestudy.util.TransactionManager;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static com.zerobase.zerobasestudy.entity.bookmark.InitWifiBookmark.*;
import static com.zerobase.zerobasestudy.entity.bookmark.InitBookmark.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookmarkRepositoryJdbcTest {
    BookmarkRepository bookmarkRepository;
    TransactionManager transactionManager;
    WifiRepository wifiRepository;
    WifiBookmarkRepository wifiBookmarkRepository;

    @BeforeEach
    void beforeEach(){
        transactionManager = TransactionManagerSingleton.getInstance();
        bookmarkRepository = new BookmarkRepositoryJdbc();
        wifiRepository = new WifiRepositoryJdbc();
        wifiBookmarkRepository = new WifiBookmarkRepositoryJdbc();
        transactionManager.getTransaction();

    }

    @AfterEach
    void afterEach(){
        transactionManager.rollback();
    }


    /** 북마크 등록 */
    @Test
    void save(){
        Bookmark bookmark = getBookmark();
        int result = bookmarkRepository.save(bookmark);
        Bookmark findBookmark = bookmarkRepository.findById(1L).orElseGet(null);

        assertEquals(bookmark, findBookmark);
        assertEquals(1, result);
    }


    /** 북마크 이름 중복조회 */
    @Test
    @DisplayName("북마크이름이 이미 존재하면 true 가 나와야한다")
    void findByName(){
        Bookmark bookmark = getBookmark();
        String name1 = bookmark.getName();
        String name2 = "내집";
        bookmarkRepository.save(bookmark);


        boolean trueFlag = bookmarkRepository.findByName(name1);
        boolean falseFlag = bookmarkRepository.findByName(name2);

        assertTrue(trueFlag);
        assertFalse(falseFlag);
    }


    /** 북마크 전체 조회 */
    @Test
    void findAll(){
        List<Bookmark> bookmarks = getBookmarks();
        int size = bookmarks.size();
        for (Bookmark bookmark : bookmarks) {
            bookmarkRepository.save(bookmark);
        }


        List<Bookmark> findBookmarks = bookmarkRepository.findAll(null, null);

        assertEquals(size, findBookmarks.size());
    }

    /** 와이파이가 등록된 북마크 제외한 전체리스트 */
    @Test
    void findAllExcludingWifi(){
        List<Bookmark> bookmarks = getBookmarks();
        Long firstId = bookmarks.get(0).getId();
        for (Bookmark bookmark : bookmarks) {
            bookmarkRepository.save(bookmark);
        }

        List<Wifi> wifiList = new ArrayList<>();
        Wifi wifi = InitWifi.getWifi();
        wifiList.add(wifi);
        wifiRepository.save(wifiList);

        Wifi findWifi = wifiRepository.findById(wifi.getId()).orElseGet(null);
        Bookmark findBookmark = bookmarkRepository.findById(firstId).orElseGet(null);

        WifiBookmark wifiBookmark = getWifiBookmark(findWifi, findBookmark);

        wifiBookmarkRepository.save(wifiBookmark);

        //when
        List<Bookmark> findBookmarks = bookmarkRepository.findAllExcludingWifi(findWifi.getId(), null, null);


        //then
        boolean flag = findBookmarks.stream().anyMatch(bookmark -> bookmark.getId().equals(findBookmark.getId()));
        assertFalse(flag);
    }

    /** 북마크 수정 */
    @Test
    void update_name(){
        Bookmark bookmark = getBookmark();
        String updateName = "학교";

        bookmarkRepository.save(bookmark);
        Bookmark findBookmark = bookmarkRepository.findById(bookmark.getId()).orElseGet(null);

        bookmarkRepository.update(findBookmark.getId(), updateName, bookmark.getSequenceNum());

        Bookmark updatedBookmark = bookmarkRepository.findById(bookmark.getId()).orElseGet(null);
        assertEquals(updateName, updatedBookmark.getName());
        assertEquals(bookmark.getSequenceNum() , updatedBookmark.getSequenceNum());

    }

    /** 북마크 수정 */
    @Test
    void update_sequenceNum(){
        Bookmark bookmark = getBookmark();
        Integer updateSequenceNum = 2;

        bookmarkRepository.save(bookmark);
        Bookmark findBookmark = bookmarkRepository.findById(bookmark.getId()).orElseGet(null);

        bookmarkRepository.update(findBookmark.getId(), bookmark.getName(), updateSequenceNum);

        Bookmark updatedBookmark = bookmarkRepository.findById(bookmark.getId()).orElseGet(null);
        assertEquals(updateSequenceNum, updatedBookmark.getSequenceNum());
        assertEquals(bookmark.getName() , updatedBookmark.getName());

    }

    /** 북마크 삭제 */
    @Test
    void delete(){
        Bookmark bookmark = getBookmark();

        bookmarkRepository.deleteById(bookmark.getId());

        assertThrows(IllegalArgumentException.class, () ->
                bookmarkRepository.findById(bookmark.getId()).orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 북마크")));

    }

}
