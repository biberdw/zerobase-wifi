package com.zerobase.zerobasestudy.config.view;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ModelAndView {
    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

}
