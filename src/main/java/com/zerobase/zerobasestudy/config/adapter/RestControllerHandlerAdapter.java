package com.zerobase.zerobasestudy.config.adapter;

import com.zerobase.zerobasestudy.config.view.ModelAndView;
import com.zerobase.zerobasestudy.controller.Controller;
import com.zerobase.zerobasestudy.controller.RestController;
import com.zerobase.zerobasestudy.util.ResponseEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.zerobase.zerobasestudy.util.constutil.HttpConst.*;
import static com.zerobase.zerobasestudy.util.constutil.HttpConst.DELETE;

public class RestControllerHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof RestController);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        String method = request.getMethod();
        RestController controller = (RestController) handler;
        Map<String, String> paramMap = createPramMap(request);
        ResponseEntity<Object> responseEntity = null;
        Map<String, Object> model = new HashMap<>();
        switch (method){
            case GET : responseEntity = controller.get(paramMap);break;
            case POST : responseEntity = controller.post(paramMap);break;
            case PUT : responseEntity = controller.put(paramMap);break;
            case DELETE : responseEntity = controller.delete(paramMap);break;

        }

        ModelAndView mv = new ModelAndView("json");
        model.put("response", responseEntity);
        mv.setModel(model);
        return mv;

    }

    private static Map<String, String> createPramMap(HttpServletRequest request) {
        //쿼리스트링
        Map<String , String> paramMap = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }

        //json
        try {
            BufferedReader reader;
            InputStream inputStream = request.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();
            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = reader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
            paramMap.put("json",stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return paramMap;
    }
}
