package com.zerobase.zerobasestudy.service.history;

import com.zerobase.zerobasestudy.dto.history.HistoryDto;
import com.zerobase.zerobasestudy.util.constutil.OrderBy;

import java.util.List;

public interface HistoryService {

    void delete(Long id);

    void save(Double latitude, Double longitude);

    List<HistoryDto.Response> getHistoryDtoList(Integer limit, OrderBy orderBy);
}
