package com.nitin.prizy.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CORSInterceptor extends HandlerInterceptorAdapter
{
    private static Logger LOGGER = LoggerFactory.getLogger(CORSInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        LOGGER.info("REQUEST " + request.getRequestURI());
        LOGGER.info("Access-Control-Allow-Origin=" + request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Origin", SecurityUtil.instance.getCORSSupportedOrigin(request.getHeader("Origin")));
        return super.preHandle(request, response, handler);
    }

}
