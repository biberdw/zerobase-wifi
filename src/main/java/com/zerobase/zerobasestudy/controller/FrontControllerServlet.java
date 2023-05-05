package com.zerobase.zerobasestudy.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.zerobasestudy.config.adapter.ControllerHandlerAdapter;
import com.zerobase.zerobasestudy.config.adapter.HandlerAdapter;
import com.zerobase.zerobasestudy.config.adapter.RestControllerHandlerAdapter;
import com.zerobase.zerobasestudy.config.init.ObjectMapperSingleton;
import com.zerobase.zerobasestudy.config.view.ModelAndView;
import com.zerobase.zerobasestudy.config.view.View;
import com.zerobase.zerobasestudy.config.view.ViewResolver;
import com.zerobase.zerobasestudy.controller.bookmark.BookmarkController;
import com.zerobase.zerobasestudy.controller.bookmark.BookmarkRestController;
import com.zerobase.zerobasestudy.controller.bookmark.WifiBookmarkController;
import com.zerobase.zerobasestudy.controller.history.HistoryController;

import com.zerobase.zerobasestudy.controller.wifi.WifiController;
import com.zerobase.zerobasestudy.repository.bookmark.*;
import com.zerobase.zerobasestudy.repository.history.HistoryRepository;
import com.zerobase.zerobasestudy.repository.history.HistoryRepositoryJdbcV1;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepositoryJdbcV1;
import com.zerobase.zerobasestudy.service.bookmark.BookmarkService;
import com.zerobase.zerobasestudy.service.bookmark.BookmarkServiceImpl;
import com.zerobase.zerobasestudy.service.bookmark.WifiBookmarkService;
import com.zerobase.zerobasestudy.service.bookmark.WifiBookmarkServiceImpl;
import com.zerobase.zerobasestudy.service.history.HistoryService;
import com.zerobase.zerobasestudy.service.history.HistoryServiceImpl;
import com.zerobase.zerobasestudy.service.wifi.WifiService;
import com.zerobase.zerobasestudy.service.wifi.WifiServiceImpl;
import com.zerobase.zerobasestudy.config.init.DbInitializer;
import com.zerobase.zerobasestudy.util.HttpHeaders;
import com.zerobase.zerobasestudy.util.HttpStatus;
import com.zerobase.zerobasestudy.util.ResponseEntity;
import com.zerobase.zerobasestudy.util.TxManagerJdbc;
import com.zerobase.zerobasestudy.util.proxy.ExceptionHandler;
import com.zerobase.zerobasestudy.util.proxy.TxHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Proxy;
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


//        뷰이름이 리다이렉트 / json / 페이지이름 검사 후 뷰페이지 경로 반환받음
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
        //트랜잭션 매니저
        TxManagerJdbc txManager = new TxManagerJdbc(dataSource);
        //ObjectMapper
        ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();

        //히스토리 초기화
        HistoryRepository historyRepository = new HistoryRepositoryJdbcV1(txManager);
        HistoryService historyService = new HistoryServiceImpl(historyRepository);
        HistoryService historyServiceTxProxy = (HistoryService) Proxy.newProxyInstance(HistoryService.class.getClassLoader(),
                new Class[]{HistoryService.class},
                new TxHandler(historyService, txManager));
        Controller historyController = new HistoryController(historyServiceTxProxy);
        Controller historyExceptionProxy = (Controller) Proxy.newProxyInstance(Controller.class.getClassLoader(),
                new Class[]{Controller.class},
                new ExceptionHandler(historyController));


        //북마크 초기화
        BookmarkRepository bookmarkRepository = new BookmarkRepositoryJdbcV1(txManager);
        BookmarkService bookmarkService = new BookmarkServiceImpl(bookmarkRepository);
        BookmarkService bookmarkServiceTxProxy = (BookmarkService) Proxy.newProxyInstance(BookmarkService.class.getClassLoader(),
                new Class[]{BookmarkService.class},
                new TxHandler(bookmarkService, txManager));
        Controller bookmarkController =  new BookmarkController(bookmarkServiceTxProxy);
        Controller bookmarkExceptionProxy = (Controller) Proxy.newProxyInstance(Controller.class.getClassLoader(),
                new Class[]{Controller.class},
                new ExceptionHandler(bookmarkController));


        //와이파이 초기화
        WifiRepository wifiRepository = new WifiRepositoryJdbcV1(txManager);
        WifiService wifiService = new WifiServiceImpl(wifiRepository);
        WifiService wifiServiceTxProxy = (WifiService) Proxy.newProxyInstance(WifiService.class.getClassLoader(),
                new Class[]{WifiService.class},
                new TxHandler(wifiService, txManager));
        Controller wifiController = new WifiController(wifiServiceTxProxy, historyServiceTxProxy, bookmarkServiceTxProxy);
        Controller wifiExceptionProxy = (Controller) Proxy.newProxyInstance(Controller.class.getClassLoader(),
                new Class[]{Controller.class},
                new ExceptionHandler(wifiController));


        //와이파이_북마크 초기화
        WifiBookmarkRepository wifiBookmarkRepository = new WifiBookmarkRepositoryJdbcV1(txManager);
        WifiBookmarkService wifiBookmarkService = new WifiBookmarkServiceImpl(wifiRepository, bookmarkRepository, wifiBookmarkRepository);
        WifiBookmarkService wifiBookmarkServiceTxProxy = (WifiBookmarkService) Proxy.newProxyInstance(WifiBookmarkService.class.getClassLoader(),
                new Class[]{WifiBookmarkService.class},
                new TxHandler(wifiBookmarkService, txManager));
        Controller wifiBookmarkController = new WifiBookmarkController(wifiBookmarkServiceTxProxy);
        Controller wifiBookmarkExceptionProxy = (Controller) Proxy.newProxyInstance(Controller.class.getClassLoader(),
                new Class[]{Controller.class},
                new ExceptionHandler(wifiBookmarkController));

        //즐겨찾기 중복검사 컨트롤러
        RestController bookmarkRestController = new BookmarkRestController(bookmarkServiceTxProxy,objectMapper);


        handleMappingMap.put("/apps/histories", historyExceptionProxy);
        handleMappingMap.put("/apps/wifi", wifiExceptionProxy);
        handleMappingMap.put("/apps/bookmarks", bookmarkExceptionProxy);
        handleMappingMap.put("/apps/bookmarks/wifi" , wifiBookmarkExceptionProxy);
        handleMappingMap.put("/apps/bookmarks/name", bookmarkRestController);
    }

    /** 어답터 등록 */
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerHandlerAdapter());
        handlerAdapters.add(new RestControllerHandlerAdapter());
    }


}
