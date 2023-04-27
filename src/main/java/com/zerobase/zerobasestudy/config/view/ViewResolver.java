package com.zerobase.zerobasestudy.config.view;

public class ViewResolver {

    private String prefix;
    private String suffix;


    public ViewResolver() {
        setPrefix("/WEB-INF/views/");
        setSuffix(".jsp");
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public View resolveViewName(String viewName){
        if (viewName.startsWith("redirect:")) {
            return new RedirectView(viewName.substring("redirect:".length()));
        } else {
            return new BasicView(prefix + viewName + suffix);
        }
    }



}
