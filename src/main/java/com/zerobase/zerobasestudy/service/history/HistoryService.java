package com.zerobase.zerobasestudy.service.history;

import com.zerobase.zerobasestudy.dto.history.HistoryDto;
import com.zerobase.zerobasestudy.util.constutil.OrderBy;

import java.util.List;

public interface HistoryService {
    /** 히스토리 등록 */
    void save(Double latitude, Double longitude);

    /** 히스토리 20개 조회 */
    List<HistoryDto.Response> getHistoryDtoList();

    /** 히스토리 삭제 */
    void delete(Long id);
}
