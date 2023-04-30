package com.zerobase.zerobasestudy.controller;


import com.zerobase.zerobasestudy.config.adapter.ControllerHandlerAdapter;
import com.zerobase.zerobasestudy.config.adapter.HandlerAdapter;
import com.zerobase.zerobasestudy.config.view.ModelAndView;
import com.zerobase.zerobasestudy.config.view.View;
import com.zerobase.zerobasestudy.config.view.ViewResolver;
import com.zerobase.zerobasestudy.controller.bookmark.BookmarkController;
import com.zerobase.zerobasestudy.controller.history.HistoryController;

import com.zerobase.zerobasestudy.controller.wifi.WifiController;
import com.zerobase.zerobasestudy.repository.bookmark.BookmarkRepository;
import com.zerobase.zerobasestudy.repository.bookmark.BookmarkRepositoryJdbc;
import com.zerobase.zerobasestudy.repository.history.HistoryRepository;
import com.zerobase.zerobasestudy.repository.history.HistoryRepositoryJdbc;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepositoryJdbc;
import com.zerobase.zerobasestudy.service.bookmark.BookmarkService;
import com.zerobase.zerobasestudy.service.bookmark.BookmarkServiceImpl;
import com.zerobase.zerobasestudy.service.history.HistoryService;
import com.zerobase.zerobasestudy.service.history.HistoryServiceImpl;
import com.zerobase.zerobasestudy.service.wifi.WifiService;
import com.zerobase.zerobasestudy.service.wifi.WifiServiceImpl;
import com.zerobase.zerobasestudy.config.init.DbInitializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;


@WebServlet("/apps/*")
public class FrontControllerServlet extends HttpServlet {

    private final Map<String, Object> handleMappingMap = new HashMap<>();
    private final List<HandlerAdapter> handlerAdapters = new ArrayList<>();



    public FrontControllerServlet() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //URL 에 맞는 등록된 컨트롤러를 찾아온다
        Object handler = getHandler(request);


        if (handler == null) {
            throw new IllegalStateException("요청하신 주소를 찾지 못하였습니다 {" + request.getRequestURI() + "}");
        }


        //컨트롤러를 지원하는 어답터를 찾아보고 등록된 어답터가 있으면 가져온다
        HandlerAdapter adapter = getHandlerAdapter(handler);

        //어답터로 애플리케이션 로직 수행 후 모델과 뷰이름 반환받기
        ModelAndView mv = adapter.handle(request, response, handler);

        //뷰이름이 리다이렉트인지 아닌지 검사 후 뷰페이지 경로 반환받음
        ViewResolver viewResolver = new ViewResolver();
        View view = viewResolver.resolveViewName(mv.getViewName());

        //반환받은 뷰로 페이지 렌더링
        view.render(mv.getModel(), request, response);

    }

    /** 컨트롤러를 지원하는 어답터를 찾는다 */
    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다. handler=" + handler);
    }

    /** url 과 일치하는 컨트롤러를 Map 에서 찾아준다 */
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handleMappingMap.get(requestURI);

    }

    /** 각 객체들을 싱글톤으로 관리할 수 있도로 초기화 작업 및 url 에 각 컨트롤러 등록 */
    private void initHandlerMappingMap() {
        DataSource dataSource = DbInitializer.getDataSource();
        //히스토리 초기화
        HistoryRepository historyRepository = new HistoryRepositoryJdbc(dataSource);
        HistoryService historyService = new HistoryServiceImpl(historyRepository);
        Controller historyController = new HistoryController(historyService);

        //북마크 초기화
        BookmarkRepository bookmarkRepository = new BookmarkRepositoryJdbc(dataSource);
        BookmarkService bookmarkService = new BookmarkServiceImpl(bookmarkRepository);
        Controller bookmarkController =  new BookmarkController(bookmarkService);

        //와이파이 초기화
        WifiRepository wifiRepository = new WifiRepositoryJdbc(dataSource);
        WifiService wifiService = new WifiServiceImpl(wifiRepository);
        Controller wifiController = new WifiController(wifiService, historyService, bookmarkService);




        handleMappingMap.put("/apps/histories", historyController);
        handleMappingMap.put("/apps/wifi", wifiController);
        handleMappingMap.put("/apps/bookmarks", bookmarkController);
    }

    /** 어답터 등록 */
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerHandlerAdapter());
    }


}
