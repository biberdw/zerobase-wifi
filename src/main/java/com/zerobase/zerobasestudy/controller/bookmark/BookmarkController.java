package com.zerobase.zerobasestudy.controller.bookmark;

import com.zerobase.zerobasestudy.controller.Controller;
import com.zerobase.zerobasestudy.dto.bookmark.BookmarkDto;
import com.zerobase.zerobasestudy.service.bookmark.BookmarkService;

import java.util.List;
import java.util.Map;

public class BookmarkController implements Controller {
    BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

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
        Integer sequence = isValidInt(sequenceStr);

        bookmarkService.save(name, sequence);

        return "redirect:/apps/bookmarks";
    }




    public String put(Map<String, String> paramMap, Map<String, Object> model) {
        String name = paramMap.get("name");
        String sequenceStr = paramMap.get("sequenceNum");

        if(name.equals("") || name == null || sequenceStr.equals("") || sequenceStr == null){
            throw new IllegalArgumentException("즐겨찾기 이름과 순서는 공백 또는 비어있을 수 없습니다");
        }

        String idStr = paramMap.get("id");

        Integer sequence = isValidInt(sequenceStr);
        Long id = isValidLong(idStr);
        bookmarkService.update(id, name, sequence);

        return "redirect:/apps/bookmarks";
    }


    public String delete(Map<String, String> paramMap, Map<String, Object> model) {

        String idStr = paramMap.get("id");
        Long id = isValidLong(idStr);
        bookmarkService.delete(id);
        return "redirect:/apps/bookmarks";
    }

    private Long isValidLong(String idStr) {
        try{
            Long value = Long.parseLong(idStr);
            return value;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("잘못된 필드 = " + e);
        }
    }

    private Integer isValidInt(String sequenceStr) {
        try{
            Integer value = Integer.parseInt(sequenceStr);
            return value;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("잘못된 필드 = " + e);
        }
    }
}
