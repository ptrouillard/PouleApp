package com.pedro.raspberry.poule.ui.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {

    public final static String SECU_CODE = "130713";

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String secu = request.getParameter("secu");

        logger.info("Security code received : " + secu);

        if (request.getServletPath().startsWith("/door") && "post".equalsIgnoreCase(request.getMethod())) {
            if (!"/door/save".equals(request.getServletPath())) {
                logger.info("Security code received : " + secu);
                if (!SECU_CODE.equals(secu)) {
                    logger.error("invalid secu code");
                    response.sendRedirect("/door?secu_error=true");
                    return false;
                }
                logger.info("secu code is OK");
            }
        }
       return true;
    }
}
