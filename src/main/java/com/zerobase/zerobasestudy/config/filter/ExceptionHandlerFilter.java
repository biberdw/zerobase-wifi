package com.zerobase.zerobasestudy.config.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/** 예외 처리를 위한 필터 */
@WebFilter(filterName = "ExceptionHandlerFilter", urlPatterns = "/*")
public class ExceptionHandlerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            // 예외 처리 로직 수행

            request.setAttribute("errorMessage", "Unexpected error occurred");
            request.setAttribute("exceptionMessage", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}