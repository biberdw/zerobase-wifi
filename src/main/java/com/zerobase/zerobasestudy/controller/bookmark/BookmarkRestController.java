package com.zerobase.zerobasestudy.controller.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.zerobasestudy.controller.RestController;
import com.zerobase.zerobasestudy.dto.bookmark.BookmarkDto;
import com.zerobase.zerobasestudy.service.bookmark.BookmarkService;
import com.zerobase.zerobasestudy.util.HttpHeaders;
import com.zerobase.zerobasestudy.util.HttpStatus;
import com.zerobase.zerobasestudy.util.ResponseEntity;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static com.zerobase.zerobasestudy.util.HttpHeaders.*;

@RequiredArgsConstructor
public class BookmarkRestController implements RestController {
    
    private final BookmarkService bookmarkService;
    private final ObjectMapper objectMapper;

    /** 즐겨찾기 이름 중복검사 */
    public ResponseEntity<Object> get(Map<String, String> paramMap) {

        String param = paramMap.get("json");

        BookmarkDto.Request request;
        try {
            request = objectMapper.readValue(param, BookmarkDto.Request.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String name = request.getName();
        boolean flag = bookmarkService.existByName(name);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CONTENT_TYPE, "application/json; charset=UTF-8");
        if(flag){
            return new ResponseEntity<>(httpHeaders, null, HttpStatus.CONFLICT);
        }else {
            return new ResponseEntity<>(httpHeaders, null, HttpStatus.OK);
        }
    }
}
