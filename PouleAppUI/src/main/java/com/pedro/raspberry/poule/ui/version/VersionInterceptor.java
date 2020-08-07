package com.pedro.raspberry.poule.ui.version;

import com.pedro.raspberry.poule.ui.remoteAddr.RemoteAddrHolder;
import com.pedro.raspberry.poule.ui.remoteAddr.RemoteAddressInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

public class VersionInterceptor implements HandlerInterceptor  {
    private static Logger logger = LoggerFactory.getLogger(VersionInterceptor.class.getName());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object version = request.getSession(true).getAttribute("version");
        if (version == null) {
            // get version from properties
            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("/version.properties"));
            Object version1 = properties.get("version");
            request.getSession().setAttribute("version", version1);
        }
        return true;
    }
}
