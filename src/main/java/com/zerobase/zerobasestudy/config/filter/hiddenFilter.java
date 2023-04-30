package com.zerobase.zerobasestudy.config.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
/** PUT / DELETE 메소드 처리를 위한 필터 */

@WebFilter("/apps/*")
public class hiddenFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("UTF-8");

        // POST 요청이면서 "_method" 파라미터가 존재할 경우 메소드를 변경
        if ("POST".equals(request.getMethod()) && request.getParameter("_method") != null) {
            String method = request.getParameter("_method").toUpperCase(); //PUT / DELETE

            //넘겨받은 파라미터를 request 의 http 메소드로 등록
            HttpMethodWrapper wrapper = new HttpMethodWrapper(request, method);
            chain.doFilter(wrapper, res);
        } else {
            chain.doFilter(request, res);
        }


    }


    private static class HttpMethodWrapper extends HttpServletRequestWrapper {
        private final String method;

        public HttpMethodWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return method;
        }
    }
}

