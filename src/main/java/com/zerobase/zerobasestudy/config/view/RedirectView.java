package com.zerobase.zerobasestudy.config.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RedirectView implements View {

    private String viewPath;

    public RedirectView(String viewPath) {
        this.viewPath = viewPath;
    }

    /** 리다이렉트 */
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(viewPath);
    }
}
