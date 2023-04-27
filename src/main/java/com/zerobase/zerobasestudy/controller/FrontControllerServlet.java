package com.zerobase.zerobasestudy.controller;


import com.zerobase.zerobasestudy.config.adapter.ControllerHandlerAdapter;
import com.zerobase.zerobasestudy.config.adapter.HandlerAdapter;
import com.zerobase.zerobasestudy.config.view.ModelAndView;
import com.zerobase.zerobasestudy.config.view.View;
import com.zerobase.zerobasestudy.config.view.ViewResolver;
import com.zerobase.zerobasestudy.controller.history.HistoryController;

import com.zerobase.zerobasestudy.controller.wifi.WifiController;
import com.zerobase.zerobasestudy.repository.history.HistoryRepository;
import com.zerobase.zerobasestudy.repository.history.HistoryRepositoryJdbc;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepository;
import com.zerobase.zerobasestudy.repository.wifi.WifiRepositoryJdbc;
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

import static javax.servlet.http.HttpServletResponse.*;

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
        Object handler = getHandler(request);


        if (handler == null) {
            String requestURI = request.getRequestURI();
            System.out.println(requestURI);
            System.out.println("NOT_FOUND");
            response.setStatus(SC_NOT_FOUND);

        }

        HandlerAdapter adapter = getHandlerAdapter(handler);

        ModelAndView mv = adapter.handle(request, response, handler);

        ViewResolver viewResolver = new ViewResolver();
        View view = viewResolver.resolveViewName(mv.getViewName());
        view.render(mv.getModel(), request, response);

    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handleMappingMap.get(requestURI);

    }


    private void initHandlerMappingMap() {
        DataSource dataSource = DbInitializer.getDataSource();
        //히스토리 초기화
        HistoryRepository historyRepository = new HistoryRepositoryJdbc(dataSource);
        HistoryService historyService = new HistoryServiceImpl(historyRepository);
        Controller historyController = new HistoryController(historyService);

        //와이파이 초기화
        WifiRepository wifiRepository = new WifiRepositoryJdbc(dataSource);
        WifiService wifiService = new WifiServiceImpl(wifiRepository);
        Controller wifiController = new WifiController(wifiService, historyService);

        handleMappingMap.put("/apps/histories", historyController);
        handleMappingMap.put("/apps/wifi", wifiController);
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerHandlerAdapter());
    }


}
