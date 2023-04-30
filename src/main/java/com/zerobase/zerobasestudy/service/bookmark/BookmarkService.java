package com.zerobase.zerobasestudy.service.bookmark;

import com.zerobase.zerobasestudy.dto.bookmark.BookmarkDto;
import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.util.Sort;

import java.util.List;

public interface BookmarkService {
    //C
    /** 북마크 등록 */
    int save(String name, Integer sequence);

    //R
    /** 북마크 조회 */
    BookmarkDto.Response getBookmarkDto(Long id);

    /** 북마크 순위정렬 조회 */
    List<BookmarkDto.Response> getDtoListOrderBySeq();
    //U
    /** 북마크 수정 */
    void update(Long id, String name, Integer sequenceNum);

    //D
    /** 북마크 삭제 */
    void delete(Long id);
}
