package com.zerobase.zerobasestudy.entity.bookmark;

import com.zerobase.zerobasestudy.entity.wifi.Wifi;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InitWifiBookmark {


    public static WifiBookmark getWifiBookmark(Wifi findWifi, Bookmark findBookmark){

        return WifiBookmark.builder()
                .wifiName(findWifi.getName())
                .bookmarkName(findBookmark.getName())
                .bookmarkId(findBookmark.getId())
                .wifiId(findWifi.getId())
                .created(LocalDateTime.now())
                .build();
    }

    public static List<WifiBookmark> getWifiBookmarks(List<Wifi> findWifiList , List<Bookmark> findBookmarks) {
        List<WifiBookmark> wifiBookmarks = new ArrayList<>();

        for (int i = 1; i <= findWifiList.size(); i++) {
            WifiBookmark wifiBookmark = WifiBookmark.builder()
                    .wifiName(findWifiList.get((i - 1)).getName())
                    .bookmarkName(findBookmarks.get((i - 1)).getName())
                    .bookmarkId(findBookmarks.get((i - 1)).getId())
                    .wifiId(findWifiList.get((i - 1)).getId())
                    .created(LocalDateTime.now())
                    .build();
            wifiBookmarks.add(wifiBookmark);
        }
        return wifiBookmarks;
    }
}