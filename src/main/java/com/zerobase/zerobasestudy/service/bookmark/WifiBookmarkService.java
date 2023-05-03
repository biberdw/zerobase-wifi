package com.zerobase.zerobasestudy.service.bookmark;


import com.zerobase.zerobasestudy.dto.bookmark.WifiBookmarkDto;

import java.util.List;

public interface WifiBookmarkService {
    /** 와이파이 즐겨찾기 등록 */
    void save(Long wifiId, Long bookmarkId);

    /** 와이파이 즐겨찾기 전체조회 */
    List<WifiBookmarkDto.Response> getWifiBookmarkDtoList();

    /** 와이파이 즐겨찾기 삭제 */
    void delete(Long wifiId, Long bookmarkId);
}
