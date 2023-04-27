package com.zerobase.zerobasestudy.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface Controller {

    String get(Map<String, String> paramMap, Map<String, Object> model);

    String post(Map<String, String> paramMap, Map<String, Object> model);

    String put(Map<String, String> paramMap, Map<String, Object> model);

    String delete(Map<String, String> paramMap, Map<String, Object> model);
}
