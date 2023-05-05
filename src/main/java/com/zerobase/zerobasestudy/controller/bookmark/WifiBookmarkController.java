package com.zerobase.zerobasestudy.controller.bookmark;

import com.zerobase.zerobasestudy.controller.Controller;
import com.zerobase.zerobasestudy.dto.bookmark.WifiBookmarkDto;
import com.zerobase.zerobasestudy.service.bookmark.WifiBookmarkService;
import com.zerobase.zerobasestudy.util.validation.StaticUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.zerobase.zerobasestudy.util.validation.StaticUtils.*;

@RequiredArgsConstructor
public class WifiBookmarkController implements Controller {

    private final WifiBookmarkService wifiBookmarkService;

    public String get(Map<String, String> paramMap, Map<String, Object> model) {

        List<WifiBookmarkDto.Response> wifiBookmarks = wifiBookmarkService.getWifiBookmarkDtoList();

        model.put("wifiBookmarks", wifiBookmarks);

        return "wifiBookmarkList";
    }


    public String post(Map<String, String> paramMap, Map<String, Object> model) {
        String wifiIdStr = paramMap.get("wifiId");
        String bookmarkIdStr = paramMap.get("bookmarkId");

        Long wifiId = isValidLong(wifiIdStr);
        Long bookmarkId = isValidLong(bookmarkIdStr);

        wifiBookmarkService.save(wifiId, bookmarkId);

        return "redirect:/apps/bookmarks/wifi";
    }

    public String delete(Map<String, String> paramMap, Map<String, Object> model){
        String wifiIdStr = paramMap.get("wifiId");
        String bookmarkIdStr = paramMap.get("bookmarkId");

        Long wifiId = isValidLong(wifiIdStr);
        Long bookmarkId = isValidLong(bookmarkIdStr);
        wifiBookmarkService.delete(wifiId, bookmarkId);
        return "redirect:/apps/bookmarks/wifi";
    }

}
