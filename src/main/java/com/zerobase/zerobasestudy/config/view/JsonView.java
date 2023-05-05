package com.zerobase.zerobasestudy.config.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.zerobasestudy.config.init.ObjectMapperSingleton;
import com.zerobase.zerobasestudy.util.HttpHeaders;
import com.zerobase.zerobasestudy.util.HttpStatus;
import com.zerobase.zerobasestudy.util.ResponseEntity;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class JsonView implements View{

    private final ObjectMapper objectMapper = ObjectMapperSingleton.getInstance();

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) model.get("response");
        Object body = responseEntity.getBody();
        HttpHeaders httpHeaders = responseEntity.getHttpHeaders();
        HttpStatus httpStatus = responseEntity.getHttpStatus();

        String json = "";
        if(body != null){
            json = objectMapper.writeValueAsString(body);
        }

        if(httpHeaders != null){
            httpHeaders.getHeaders().forEach((headerName,headerValues) ->
                    headerValues.stream().forEach((headerValue) -> response.setHeader(headerName, headerValue)));
        }

        response.setStatus(httpStatus.value());
        response.getWriter().write(json);


    }
}
