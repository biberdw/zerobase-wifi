package com.zerobase.zerobasestudy.controller.history;

import com.zerobase.zerobasestudy.controller.Controller;
import com.zerobase.zerobasestudy.dto.history.HistoryDto;
import com.zerobase.zerobasestudy.service.history.HistoryService;
import com.zerobase.zerobasestudy.util.constutil.OrderBy;


import java.util.List;
import java.util.Map;

import static com.zerobase.zerobasestudy.util.constutil.OrderBy.*;

public class HistoryController implements Controller {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    public String get(Map<String, String> paramMap, Map<String, Object> model) {
        Integer limit = null;
        List<HistoryDto.Response> histories = historyService.getHistoryDtoList(limit, ID_DESC);


        model.put("histories", histories);
        return "history";
    }


    public String post(Map<String, String> paramMap, Map<String, Object> model) {
        return null;
    }


    public String put(Map<String, String> paramMap, Map<String, Object> model) {
        return null;
    }


    public String delete(Map<String, String> paramMap, Map<String, Object> model){
        String idStr = paramMap.get("id");

        Long id = isValidLong(idStr);
        historyService.delete(id);
        return "redirect:/apps/histories";

    }

    private static Long isValidLong(String id) {
        try{
            Long value = Long.parseLong(id);
            return value;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("잘못된 필드 = " + e);
        }
    }
}
