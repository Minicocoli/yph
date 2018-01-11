package com.yph.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 主要是请求日志拦截
 *
 * @author : Administrator Hzhan
 * @create ：2017/12/23
 **/
@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private Long startDate =null;

    private String requestURI =null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startDate = System.currentTimeMillis();
        requestURI = request.getRequestURI();
        int remotePort = request.getRemotePort();
        String remoteAddr = request.getRemoteAddr();
//        请求参数
        String queryString = request.getQueryString();
        log.info("【请求地址】--------> {}",remoteAddr+":"+remotePort+requestURI);
        if("GET".equals(request.getMethod())){
            log.info("【请求参数】--------> {}",queryString);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        super.afterCompletion(request, response, handler, e);
        log.info("[  执行 {} 方法耗时  : ]  ---------------->  {} 毫秒" ,requestURI,System.currentTimeMillis()-startDate);
    }
}
