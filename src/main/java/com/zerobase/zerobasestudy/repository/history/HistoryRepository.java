package com.zerobase.zerobasestudy.repository.history;

import com.zerobase.zerobasestudy.entity.history.History;
import com.zerobase.zerobasestudy.util.constutil.OrderBy;

import java.util.List;
import java.util.Optional;


public interface HistoryRepository {
    int save(Double latitude, Double longitude);

    Optional<History> findById(Long id);

    int deleteById(Long id);

    List<History> findAll(Integer limit, OrderBy orderBy );

}
