package com.pedro.raspberry.poule.ui;

import com.pedro.raspberry.poule.ui.remoteAddr.RemoteAddressInterceptor;
import com.pedro.raspberry.poule.ui.security.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor());
        registry.addInterceptor(new RemoteAddressInterceptor());
    }
}
