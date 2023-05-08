package com.zerobase.zerobasestudy.repository.history;

import com.zerobase.zerobasestudy.entity.history.History;
import com.zerobase.zerobasestudy.util.Sort;
import com.zerobase.zerobasestudy.util.constutil.OrderBy;

import java.util.List;
import java.util.Optional;


public interface HistoryRepository {
    /** 히스토리 등록 */
    int save(History history);

    /** 히스토리 단건 조회 */
    Optional<History> findById(Long id);

    /** 히스토리 전체 조회 */
    List<History> findAll(Integer limit, Sort sort);

    /** 히스토리 단건 삭제 */
    int deleteById(Long id);

}
