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
import com.zerobase.zerobasestudy.util.validation.StaticUtils;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static com.zerobase.zerobasestudy.util.HttpHeaders.*;

@RequiredArgsConstructor
public class BookmarkRestController implements RestController {
    
    private final BookmarkService bookmarkService;
    private final ObjectMapper objectMapper;

    /** 즐겨찾기 이름 중복검사 */
    public ResponseEntity<Object> get(Map<String, String> paramMap) {

        String name = paramMap.get("name");
        String idStr = paramMap.get("id");

        Long id = StaticUtils.isValidLong(idStr);

        boolean flag = bookmarkService.existByName(name, id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CONTENT_TYPE, "application/json; charset=UTF-8");

        if(flag){ //이미 존재하면
            return new ResponseEntity<>(httpHeaders, null, HttpStatus.CONFLICT);
        }else { //존재하지 않으면
            return new ResponseEntity<>(httpHeaders, null, HttpStatus.OK);
        }
    }

//    public ResponseEntity<Object> post(Map<String, String> paramMap) {
//  포스트 테스트해봤음
//        String param = paramMap.get("json");
//        String idStr = paramMap.get("id");
//
//        Long id = StaticUtils.isValidLong(idStr);
//
//        BookmarkDto.Request request;
//        try {
//            request = objectMapper.readValue(param, BookmarkDto.Request.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        String name = request.getName();
//        boolean flag = bookmarkService.existByName(name, id);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(CONTENT_TYPE, "application/json; charset=UTF-8");
//
//        if(flag){ //이미 존재하면
//            return new ResponseEntity<>(httpHeaders, null, HttpStatus.CONFLICT);
//        }else { //존재하지 않으면
//            return new ResponseEntity<>(httpHeaders, null, HttpStatus.OK);
//        }

//    }
}
