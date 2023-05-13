package com.zerobase.zerobasestudy.repository.bookmark;

import com.zerobase.zerobasestudy.entity.bookmark.Bookmark;
import com.zerobase.zerobasestudy.util.Sort;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository {
    //C
    /** 북마크 등록 */
    int save(Bookmark bookmark);

    //R
    /** 북마크 단건 조회 */
    Optional<Bookmark> findById(Long id);

    /** 북마크 이름 중복조회
     * 있으면 true
     * 없으면 flase
     * */
    boolean findByName(String name);

    /** 북마크 전체 조회 */
    List<Bookmark> findAll(Integer limit, Sort sort);

    /** 와이파이가 등록된 북마크 제외한 전체리스트 */
    List<Bookmark> findAllExcludingWifi(Long wifiId, Integer limit, Sort sort);

    //U
    /** 북마크 수정 */
    int update(Long id, String name, Integer sequenceNum);

    //D

    /** 북마크 삭제 */
    int deleteById(Long id);
}
