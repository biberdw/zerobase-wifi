package com.zerobase.zerobasestudy.entity.bookmark;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class Bookmark {

    private Long id;
    private String name;
    private Integer sequenceNum;
    private LocalDateTime created;
    private LocalDateTime modified;

    public void updateName(String name){
        this.name = name;
    }

    public void updateSequenceNum(Integer sequenceNum){
        this.sequenceNum = sequenceNum;
    }

    public void updateModified(){
        this.modified = LocalDateTime.now();
    }
}
