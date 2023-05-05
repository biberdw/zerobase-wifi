package com.zerobase.zerobasestudy.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface Controller {

    default String get(Map<String, String> paramMap, Map<String, Object> model) {
        return "500";
    }

    default String post(Map<String, String> paramMap, Map<String, Object> model) {
        return "500";
    }

    default String put(Map<String, String> paramMap, Map<String, Object> model) {
        return "500";
    }

    default String delete(Map<String, String> paramMap, Map<String, Object> model) {
        return "500";
    }
}
