package com.zerobase.zerobasestudy.repository.bookmark;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zerobase.zerobasestudy.config.init.DbInitializer;
import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.repository.history.HistoryRepositoryJdbc;
import com.zerobase.zerobasestudy.util.Sort;
import com.zerobase.zerobasestudy.util.TxManagerJdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.*;
import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.DRIVER;
import static org.junit.jupiter.api.Assertions.*;

class BookmarkRepositoryJdbcTest {

    BookmarkRepository repository;

    @BeforeEach
    void beforeEach() {
        DataSource dataSource = DbInitializer.getDataSource();
        TxManagerJdbc txManagerJdbc = new TxManagerJdbc(dataSource);

        repository = new BookmarkRepositoryJdbcV1(txManagerJdbc);
    }


    @Test
    @DisplayName("북마크 등록")
    void 북마크등록() {
        Bookmark bookmark = Bookmark.builder()
                .name("내집")
                .sequenceNum(1)
                .created(LocalDateTime.now())
                .build();

        repository.save(bookmark);
    }

    @Test
    @DisplayName("북마크 단건조회")
    void 북마크단건조회() {
        Bookmark bookmark = Bookmark.builder()
                .id(6L)
                .name("한국")
                .sequenceNum(1)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();

        repository.save(bookmark);

        Optional<Bookmark> find = repository.findById(bookmark.getId());
        Bookmark findBookmark = find.get();

        Assertions.assertEquals(bookmark.getName(), findBookmark.getName());


    }

    @Test
    @DisplayName("북마크 전체조회")
    void 북마크전체조회() {
        List<Bookmark> all = repository.findAll(null, null);

        Assertions.assertEquals(7,all.size());

    }


    @Test
    @DisplayName("북마크 단건삭제")
    void 북마크단건삭제(){
        int i = repository.deleteById(1L);
        Assertions.assertEquals(1, i);

    }

    @Test
    @DisplayName("북마크 와이파이 빼고 조회")
    void 북마크전체조회_와이파빼고(){
        List<Bookmark> bookmarks = repository.findAllExcludingWifi(1L, null, null);
        for (Bookmark bookmark : bookmarks) {
            System.out.println(bookmark.getId());
        }
        Assertions.assertEquals(3, bookmarks.size());
    }

    @Test
    @DisplayName("북마크 수정")
    void 북마크수정(){

        //둘중에 하나라도 다를때

        //이름은 기존과 같지만 순서가 변경됐을 때
//        repository.update(2L, null, 3);

//        Bookmark bookmark = repository.findById(2L).get();
//        Assertions.assertEquals(3, bookmark.getSequenceNum());

        //이름이 변경되고 순서는 같을 때
        repository.update(2L, "변경집", null);
        Bookmark bookmark = repository.findById(2L).get();
        Assertions.assertEquals("변경집",bookmark.getName());
        //둘다 변경 됐을 때

//        repository.update();
    }

    @Test
    void 이름중복검사(){
        Bookmark bookmark = Bookmark.builder()
                .name("내학교")
                .sequenceNum(2)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();

        repository.save(bookmark);
        boolean result = repository.findByName("내학교");
        Assertions.assertEquals(true, result);
    }
}