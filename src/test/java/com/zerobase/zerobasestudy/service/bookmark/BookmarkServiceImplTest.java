package com.zerobase.zerobasestudy.service.bookmark;

import com.zerobase.zerobasestudy.repository.bookmark.BookmarkRepository;
import com.zerobase.zerobasestudy.repository.bookmark.BookmarkRepositoryJdbc;
import com.zerobase.zerobasestudy.repository.bookmark.WifiBookmarkRepository;
import com.zerobase.zerobasestudy.repository.bookmark.WifiBookmarkRepositoryJdbc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class BookmarkServiceImplTest {

    @Mock
    BookmarkRepository bookmarkRepository;
    @Mock
    WifiBookmarkRepository wifiBookmarkRepository;

    @InjectMocks
    BookmarkServiceImpl bookmarkService;


    @Test
    @DisplayName("즐겨찾기 이름이 이미 존재하면 등록할 수 없음")
    public void save() throws Exception{
        //given
        given(bookmarkRepository.findByName(any()))
                .willReturn(true);

        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bookmarkService.save("내차", 1));

        //then
        assertEquals("이미 존재하는 즐겨찾기 이름", exception.getMessage());

    }
}