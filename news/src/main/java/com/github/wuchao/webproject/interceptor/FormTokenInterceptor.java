package com.github.wuchao.webproject.interceptor;

import com.github.wuchao.webproject.domain.FormToken;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 表单提交验证 Token，防止表单重复提交
 */
@Component
public class FormTokenInterceptor extends HandlerInterceptorAdapter {

    private final static String FORM_TOKEN_SESSION_ATTRIBUTE = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            FormToken annotation = method.getAnnotation(FormToken.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.create();
                if (needSaveSession) {
                    request.getSession(false).setAttribute(FORM_TOKEN_SESSION_ATTRIBUTE, UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        return false;
                    }
                    request.getSession(false).removeAttribute(FORM_TOKEN_SESSION_ATTRIBUTE);
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 后台验证不通过，重新生成表单Token
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            FormToken annotation = method.getAnnotation(FormToken.class);
            if (annotation != null) {
                boolean needRemoveSession = annotation.remove();
                // 表单后台验证不通过的情况
                if (needRemoveSession && !modelAndView.getViewName().startsWith("redirect:")) {
                    request.getSession(false).setAttribute(FORM_TOKEN_SESSION_ATTRIBUTE, UUID.randomUUID().toString());
                }
            }
        }
    }

    /**
     * 判断表单是否重复提交
     *
     * @param request
     * @return
     */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute(FORM_TOKEN_SESSION_ATTRIBUTE);
        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getParameter(FORM_TOKEN_SESSION_ATTRIBUTE);
        if (clientToken == null) {
            return true;
        }
        return !serverToken.equals(clientToken);
    }
}
