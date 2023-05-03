package com.zerobase.zerobasestudy.controller.wifi;

import com.zerobase.zerobasestudy.controller.Controller;
import com.zerobase.zerobasestudy.dto.bookmark.BookmarkDto;
import com.zerobase.zerobasestudy.dto.wifi.WifiApiDto;
import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import com.zerobase.zerobasestudy.service.bookmark.BookmarkService;
import com.zerobase.zerobasestudy.service.history.HistoryService;
import com.zerobase.zerobasestudy.service.wifi.WifiService;
import java.util.List;
import java.util.Map;

public class WifiController implements Controller {

    private final WifiService wifiService;
    private final HistoryService historyService;
    private final BookmarkService bookmarkService;

    public WifiController(WifiService wifiService, HistoryService historyService,BookmarkService bookmarkService) {
        this.wifiService = wifiService;
        this.historyService = historyService;
        this.bookmarkService = bookmarkService;
    }
    /** 와이파이 조회 */
    public String get(Map<String, String> paramMap, Map<String, Object> model){
        String idStr = paramMap.get("id");
        String latitudeStr = paramMap.get("latitude");
        String longitudeStr = paramMap.get("longitude");

        //id가 있었을때(단건조회)
        if(idStr != null){
            Long id = isValidLong(idStr);
            WifiDto.Response wifiDto = wifiService.getWifiDto(id);
            List<BookmarkDto.Response> bookmarks = bookmarkService.getDtoListExcludingWifi(id);
            model.put("bookmarks", bookmarks);
            model.put("wifiDto", wifiDto);
            return "wifiDetail";
        }


        //좌표가 있었을때
        if(latitudeStr != null && longitudeStr != null){
            //유효성 검증 및 초기화
            Double latitude = isValidDouble(latitudeStr);
            Double longitude = isValidDouble(longitudeStr);
            Integer limit = 20;

            //히스토리 저장
            historyService.save(latitude, longitude);

            //위치정보와 가장 가까운 list 조회
            List<WifiDto.Response> list = wifiService.getNearbyWifiDtoList(latitude, longitude, limit);
            model.put("latitude", String.valueOf(latitude));
            model.put("longitude", String.valueOf(longitude));
            model.put("wifiList", list);

        }

        return "wifiList";
    }

    /** 오픈 api 를 통한 와이파이 와이파이 등록 */
    public synchronized String post(Map<String, String> paramMap, Map<String, Object> model)  {
        //전체 db 삭제
        wifiService.deleteAll();

        int start = 1; //조회할 데이터 시작점
        int pageSize = 999; //조회할 데이터 갯수
        int end = start + pageSize -1; //조회할 마지막 데이터

        //전체 갯수를 가져오기 위해 한번 처음 호출
        WifiApiDto wifiDto = WifiApiDto.fetchWifiApiDto(start, end);
        //전체 갯수를 가져온다
        int total = wifiDto.getTbPublicWifiInfo().getTotalCount();


        //가져온 dto 를 엔티티로 변환
        List<Wifi> wifiList = wifiDto.toEntities();


        //db 에 삽입 후 삽입된 행의 갯수 가져오기
        int insertCount = wifiService.save(wifiList);

        //조회할 데이터 시작점 초기화
        start = end + 1;

        //토탈까지 반복하며 데이터를 조회 후 db 에 삽입
        while(start < total) {
            end = Math.min(start + pageSize -1, total);

            wifiDto = WifiApiDto.fetchWifiApiDto(start, end);
            wifiList = wifiDto.toEntities();

            insertCount += wifiService.save(wifiList);

            start += pageSize;
        }


        model.put("count", insertCount);
        return "count";
    }


    public String put(Map<String, String> paramMap, Map<String, Object> model) {
        return null;
    }


    public String delete(Map<String, String> paramMap, Map<String, Object> model) {
        return null;
    }

    private static Long isValidLong(String id) {
        try{
            Long value = Long.parseLong(id);
            return value;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("잘못된 필드 = " + e);
        }
    }

    /** Double 자료형 검증 */
    private static Double isValidDouble(String arg) {
        try{
            Double value = Double.parseDouble(arg);
            return value;
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("잘못된 필드 = " + e);
        }
    }
}
