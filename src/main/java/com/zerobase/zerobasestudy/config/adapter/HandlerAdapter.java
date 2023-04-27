package com.zerobase.zerobasestudy.config.adapter;

import com.zerobase.zerobasestudy.config.view.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HandlerAdapter {
    /** 컨트롤러를 지원하는지 확인 */
    boolean supports(Object handler);

    /** 로직 수행 */
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
