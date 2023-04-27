package com.zerobase.zerobasestudy.config.adapter;

import com.zerobase.zerobasestudy.config.view.ModelAndView;
import com.zerobase.zerobasestudy.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.zerobase.zerobasestudy.util.constutil.HttpConst.*;
import static com.zerobase.zerobasestudy.util.constutil.HttpConst.DELETE;

public class ControllerHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        String method = request.getMethod();
        Controller controller = (Controller) handler;
        Map<String, String> paramMap = createPramMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = null;

        switch (method){
            case GET : viewName = controller.get(paramMap, model);break;
            case POST : viewName = controller.post(paramMap, model);break;
            case PUT : viewName = controller.put(paramMap, model);break;
            case DELETE : viewName = controller.delete(paramMap, model);break;

        }

        ModelAndView mv = new ModelAndView(viewName);
        mv.setModel(model);
        return mv;
    }

    private static Map<String, String> createPramMap(HttpServletRequest request) {
        Map<String , String> paramMap = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }
        return paramMap;
    }
}
