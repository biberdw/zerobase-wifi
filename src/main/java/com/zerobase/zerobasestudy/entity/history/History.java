package com.zerobase.zerobasestudy.entity.history;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class History {

    private Long id;
    private Double longitude;
    private Double latitude;
    private LocalDateTime created;

}
