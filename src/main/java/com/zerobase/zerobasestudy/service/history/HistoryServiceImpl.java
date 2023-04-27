package com.zerobase.zerobasestudy.service.history;

import com.zerobase.zerobasestudy.dto.history.HistoryDto;
import com.zerobase.zerobasestudy.entity.history.History;
import com.zerobase.zerobasestudy.repository.history.HistoryRepository;
import com.zerobase.zerobasestudy.util.constutil.OrderBy;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }



    public void delete(Long id) {

        History history = historyRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 히스토리 id=" + id));

        historyRepository.deleteById(history.getId());
    }

    public void save(Double latitude, Double longitude) {
        historyRepository.save(latitude, longitude);
    }

    public List<HistoryDto.Response> getHistoryDtoList(Integer limit, OrderBy orderBy) {
        return historyRepository.findAll(limit, orderBy).stream()
                .map(HistoryDto.Response::new)
                .collect(Collectors.toList());
    }
}
