package com.zerobase.zerobasestudy.controller.history;

import com.zerobase.zerobasestudy.controller.Controller;
import com.zerobase.zerobasestudy.dto.history.HistoryDto;
import com.zerobase.zerobasestudy.service.history.HistoryService;
import com.zerobase.zerobasestudy.util.validation.StaticUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.zerobase.zerobasestudy.util.constutil.OrderBy.*;
import static com.zerobase.zerobasestudy.util.validation.StaticUtils.*;

@RequiredArgsConstructor
public class HistoryController implements Controller {

    private final HistoryService historyService;


    /** 히스토리 전체조회 */
    public String get(Map<String, String> paramMap, Map<String, Object> model) {

        List<HistoryDto.Response> histories = historyService.getHistoryDtoList();

        model.put("histories", histories);
        return "history";
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

}
