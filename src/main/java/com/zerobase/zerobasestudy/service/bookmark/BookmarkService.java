package com.zerobase.zerobasestudy.service.bookmark;

import com.zerobase.zerobasestudy.dto.bookmark.BookmarkDto;
import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.util.Sort;

import java.util.List;

public interface BookmarkService {
    //C
    /** 북마크 등록 */
    void save(String name, Integer sequence);

    //R
    /** 북마크 조회 */
    BookmarkDto.Response getBookmarkDto(Long id);

    /** 북마크 순위정렬 조회 */
    List<BookmarkDto.Response> getDtoListOrderBySeq();

    /** 와이파이가 등록된 북마크 제외한 전체리스트 */
    List<BookmarkDto.Response> getDtoListExcludingWifi(Long wifiId);

    //U
    /** 북마크 수정 */
    void update(Long id, String name, Integer sequenceNum);

    //D
    /** 북마크 삭제 */
    void delete(Long id);

    /** 북마크 중복 조회 */
    boolean existByName(String name);
}
