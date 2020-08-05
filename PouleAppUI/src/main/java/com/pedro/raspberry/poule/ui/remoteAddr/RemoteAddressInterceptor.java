package com.pedro.raspberry.poule.ui.remoteAddr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoteAddressInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(RemoteAddressInterceptor.class.getName());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RemoteAddrHolder.set(request.getRemoteAddr());
        return true;
    }
}
