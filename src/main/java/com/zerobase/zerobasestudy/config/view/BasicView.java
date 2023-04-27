package com.zerobase.zerobasestudy.config.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class BasicView implements View {
    private String viewPath;

    public BasicView(String viewPath) {
        this.viewPath = viewPath;
    }

    /** jsp 페이지 렌더링 */
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //모델에 담겨있는 객체를 jsp 에 전달하기 위해 request 에 담기
        modelToRequestAttribute(model, request);
        // 뷰페이지로 이동
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach(request::setAttribute);
    }
}
