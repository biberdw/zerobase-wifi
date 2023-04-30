package com.zerobase.zerobasestudy.service.history;

import com.zerobase.zerobasestudy.dto.history.HistoryDto;
import com.zerobase.zerobasestudy.entity.history.History;
import com.zerobase.zerobasestudy.repository.history.HistoryRepository;
import com.zerobase.zerobasestudy.util.Sort;
import com.zerobase.zerobasestudy.util.constutil.OrderBy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }



    /** 히스토리 등록 */
    public void save(Double latitude, Double longitude) {
        historyRepository.save(latitude, longitude);
    }

    /** 히스토리 전체조회 */
    public List<HistoryDto.Response> getHistoryDtoList() {

        Sort sort = new Sort("history_id", Sort.Direction.DESC);
        return historyRepository.findAll(null, sort).stream()
                .map(HistoryDto.Response::new)
                .collect(Collectors.toList());
    }

    /** 히스토리 삭제 */
    public void delete(Long id) {

        History history = historyRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 히스토리 id=" + id));

        historyRepository.deleteById(history.getId());
    }
}
