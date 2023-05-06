package com.zerobase.zerobasestudy.controller.bookmark;

import com.zerobase.zerobasestudy.controller.Controller;
import com.zerobase.zerobasestudy.dto.bookmark.BookmarkDto;
import com.zerobase.zerobasestudy.service.bookmark.BookmarkService;
import com.zerobase.zerobasestudy.util.validation.StaticUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.zerobase.zerobasestudy.util.validation.StaticUtils.*;

@RequiredArgsConstructor
public class BookmarkController implements Controller {

    private final BookmarkService bookmarkService;



    public String get(Map<String, String> paramMap, Map<String, Object> model){

        String mode = paramMap.get("mode");
        String idStr = paramMap.get("id");


        if(mode != null){
            model.put("mode", mode);
            if(mode.equals("update")) {
                Long id = isValidLong(idStr);
                BookmarkDto.Response bookmark = bookmarkService.getBookmarkDto(id);
                model.put("bookmark", bookmark);
            }
            return "bookmarkEdit";
        }


        List<BookmarkDto.Response> bookmarks = bookmarkService.getDtoListOrderBySeq();
        model.put("bookmarks", bookmarks);
        return "bookmarkList";
    }



    public String post(Map<String, String> paramMap, Map<String, Object> model) {
        String name = paramMap.get("name");
        String sequenceStr = paramMap.get("sequenceNum");

        //유효성 검증
        isNullParameters(name, sequenceStr);
        Integer sequence = isValidInt(sequenceStr);

        //로직
        bookmarkService.save(name, sequence);

        return "redirect:/apps/bookmarks";
    }




    public String put(Map<String, String> paramMap, Map<String, Object> model) {
        String name = paramMap.get("name");
        String sequenceStr = paramMap.get("sequenceNum");
        String idStr = paramMap.get("id");

        //유효성 검증
        isNullParameters(name, sequenceStr);
        Integer sequence = isValidInt(sequenceStr);
        Long id = isValidLong(idStr);

        //로직
        bookmarkService.update(id, name, sequence);

        return "redirect:/apps/bookmarks";
    }


    public String delete(Map<String, String> paramMap, Map<String, Object> model) {

        String idStr = paramMap.get("id");
        Long id = isValidLong(idStr);
        bookmarkService.delete(id);
        return "redirect:/apps/bookmarks";
    }

    private static void isNullParameters(String name, String sequenceStr) {
        if(name.equals("") || name == null || sequenceStr.equals("") || sequenceStr == null){
            throw new IllegalArgumentException("즐겨찾기 이름과 순서는 공백 또는 비어있을 수 없습니다");
        }
    }
}
