package com.zerobase.zerobasestudy.repository.history;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zerobase.zerobasestudy.entity.history.History;
import com.zerobase.zerobasestudy.util.Sort;
import com.zerobase.zerobasestudy.util.constutil.OrderBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.*;
import static com.zerobase.zerobasestudy.util.constutil.DatabaseConst.DRIVER;
import static org.junit.jupiter.api.Assertions.*;

class HistoryRepositoryJdbcTest {

    HistoryRepository repository;

    @BeforeEach
    void beforeEach(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setDriverClassName(DRIVER);
        HikariDataSource dataSource = new HikariDataSource(config);
        repository = new HistoryRepositoryJdbc(dataSource);
    }

    @Test
    @DisplayName("전체 조회")
    void findAll() {
        Sort sort = new Sort("history_id", Sort.Direction.DESC);

        List<History> histories = repository.findAll(5, sort);
        for (History history : histories) {
            System.out.println("history.getId() = " + history.getId());
        }
        assertEquals(5, histories.size());

    }

    @Test
    @DisplayName("단건 삽입")
    void save(){
        Double latitude = 1.1212;
        Double longitude = 2.1212;

        int count = repository.save(latitude, longitude);
        Assertions.assertEquals(1, count);

    }

    @Test
    @DisplayName("단건 삭제")
    void delete(){
        int count = repository.deleteById(1L);
        Assertions.assertEquals(1, count);
    }

    @Test
    @DisplayName("단건 조회")
    void findById(){
        History history = repository.findById(2L).get();
        Assertions.assertEquals(2, history.getId());

    }
}