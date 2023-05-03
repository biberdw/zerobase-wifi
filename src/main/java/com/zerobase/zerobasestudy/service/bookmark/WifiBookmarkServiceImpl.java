package com.zerobase.zerobasestudy.service.bookmark;

import com.zerobase.zerobasestudy.dto.bookmark.WifiBookmarkDto;
import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.entity.bookmark.WifiBookmark;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.repository.bookmark.BookmarkRepository;
import com.zerobase.zerobasestudy.repository.bookmark.WifiBookmarkRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import com.zerobase.zerobasestudy.util.Sort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class WifiBookmarkServiceImpl implements WifiBookmarkService{

    private final WifiRepository wifiRepository;
    private final BookmarkRepository bookmarkRepository;
    private final WifiBookmarkRepository wifiBookmarkRepository;

    /** 와이파이 북마크 등록 */
    public void save(Long wifiId, Long bookmarkId) {
        Wifi wifi = wifiRepository.findById(wifiId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 와이파이 ID = " + wifiId));

        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 즐겨찾기 ID = " + bookmarkId));

        WifiBookmark wifiBookmark = getWifiBookmark(wifi, bookmark);

        wifiBookmarkRepository.save(wifiBookmark);
    }

    /** 와이파이 즐겨찾기 전체조회 */
    public List<WifiBookmarkDto.Response> getWifiBookmarkDtoList() {

        List<WifiBookmark> wifiBookmarks = wifiBookmarkRepository.findAll(null, null);

        List<WifiBookmarkDto.Response> dtoList = new ArrayList<>();

        if(!wifiBookmarks.isEmpty()){
            AtomicLong sequence = new AtomicLong(1);
            for (WifiBookmark wifiBookmark : wifiBookmarks) {
                WifiBookmarkDto.Response dto = new WifiBookmarkDto.Response(wifiBookmark, sequence.getAndIncrement());
                dtoList.add(dto);
            }
        }
        return dtoList;

    }

    /** 와이파이 즐겨찾기 단건삭제 */
    public void delete(Long wifiId, Long bookmarkId) {
        //findById 검증 필요 ----
        wifiBookmarkRepository.deleteById(wifiId, bookmarkId);
    }


    private static WifiBookmark getWifiBookmark(Wifi wifi, Bookmark bookmark) {
        return WifiBookmark.builder()
                .wifiName(wifi.getName())
                .bookmarkName(bookmark.getName())
                .bookmarkId(bookmark.getId())
                .wifiId(wifi.getId())
                .build();
    }
}
