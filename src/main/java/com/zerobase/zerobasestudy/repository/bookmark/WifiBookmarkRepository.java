package com.zerobase.zerobasestudy.repository.bookmark;

import com.zerobase.zerobasestudy.entity.bookmark.WifiBookmark;
import com.zerobase.zerobasestudy.util.Sort;

import java.util.List;

public interface WifiBookmarkRepository {

    /** 와이파이_즐겨찾기 등록 */
    int save(WifiBookmark wifiBookmark);

    /** 와이파이_즐겨찾기 전체 조회 */
    List<WifiBookmark> findAll(Integer limit, Sort sort);

    /** 와이파이 삭제 */
    int deleteById(Long wifiId, Long bookmarkId);

    /** 즐겨찾기와 같은 와이파이_즐겨찾기 조회 */
    List<WifiBookmark> findAllByBookmarkId(Long bookmarkId);

    /** 즐겨찾기 이름 변경 */
    int updateBookmarkNameByBookmarkId(String name, Long bookmarkId);

    /** 즐겨찾기와 같은 와이파이_즐겨찾기 삭제 */
    int deleteByBookmarkId(Long bookmarkId);

    /** 와이파이_즐겨찾기 전체삭제 */
    int deleteAll();
}
