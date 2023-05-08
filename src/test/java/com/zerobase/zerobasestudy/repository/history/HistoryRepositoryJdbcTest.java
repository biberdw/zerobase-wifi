package com.zerobase.zerobasestudy.repository.history;

import com.zerobase.zerobasestudy.config.init.TransactionManagerSingleton;
import com.zerobase.zerobasestudy.entity.history.History;
import com.zerobase.zerobasestudy.util.TransactionManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.zerobase.zerobasestudy.entity.history.InitHistory.*;
import static org.junit.jupiter.api.Assertions.*;

public class HistoryRepositoryJdbcTest {
    HistoryRepository historyRepository;
    TransactionManager transactionManager;

    @BeforeEach
    void beforeEach() {
        transactionManager = TransactionManagerSingleton.getInstance();
        historyRepository = new HistoryRepositoryJdbc();
        transactionManager.getTransaction();
    }

    @AfterEach
    void afterEach(){
        transactionManager.rollback();
    }



    /** 히스토리 등록 */
    @Test
    void save(){
        History history = getHistory();
        int result = historyRepository.save(history);

        History findHistory = historyRepository.findById(history.getId()).orElseGet(null);

        assertEquals(history, findHistory);
        assertEquals(1, result);

    }

    /** 히스토리 전체 조회 */
    @Test
    void findAll(){
        List<History> histories = getHistories();
        histories.stream().forEach(historyRepository::save);

        List<History> findHistories = historyRepository.findAll(null, null);

        assertEquals(histories.size() , findHistories.size());
    }

    /** 히스토리 단건 삭제 */
    @Test
    void deleteById(){
        History history = getHistory();
        historyRepository.save(history);

        int result = historyRepository.deleteById(history.getId());

        assertThrows(IllegalArgumentException.class, () ->
                historyRepository.findById(history.getId()).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 히스토리")));

    }

}
