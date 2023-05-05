package com.zerobase.zerobasestudy.util.proxy;


import com.zerobase.zerobasestudy.config.view.ModelAndView;
import com.zerobase.zerobasestudy.util.exception.SqlException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class ExceptionHandler implements InvocationHandler {

    private final Object target;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        try {
            Object result = method.invoke(target, args);
            return result;
        }catch (Exception e) {

            Throwable cause = e.getCause();
            if (cause instanceof IllegalArgumentException){
                return getViewName404(e);
            }else if (cause instanceof IllegalStateException) {
                return getViewName404(e);
            }else if (cause instanceof RuntimeException) {
                return getViewName500(e);
            }else if (cause instanceof SqlException) {
                return getViewName500(e);
            }else {
                return getViewName500(e);
            }
        }
    }

    @NotNull
    private static String getViewName500(Exception e) {
        e.printStackTrace();
        return "500";
    }

    @NotNull
    private static String getViewName404(Exception e) {
        e.printStackTrace();
        return "404";
    }
}
