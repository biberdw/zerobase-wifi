package com.zerobase.zerobasestudy.controller.history;

import com.zerobase.zerobasestudy.controller.Controller;
import com.zerobase.zerobasestudy.dto.history.HistoryDto;
import com.zerobase.zerobasestudy.service.history.HistoryService;
import java.util.List;
import java.util.Map;

import static com.zerobase.zerobasestudy.util.constutil.OrderBy.*;

public class HistoryController implements Controller {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    /** 히스토리 전체조회 */
    public String get(Map<String, String> paramMap, Map<String, Object> model) {

        List<HistoryDto.Response> histories = historyService.getHistoryDtoList();

        model.put("histories", histories);
        return "history";
    }


    public String post(Map<String, String> paramMap, Map<String, Object> model) {
        return null;
    }


    public String put(Map<String, String> paramMap, Map<String, Object> model) {
        return null;
    }

    /** 히스토리 단건삭제 */
    public String delete(Map<String, String> paramMap, Map<String, Object> model){
        //파라미터로 온 id 조회
        String idStr = paramMap.get("id");

        //유효성 검증
        Long id = isValidLong(idStr);
        //히스토리 삭제
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
