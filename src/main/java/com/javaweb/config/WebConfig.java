package com.javaweb.config;

import com.javaweb.security.BuildingAuthorizationInterceptor;
import com.javaweb.security.CustomerAuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private BuildingAuthorizationInterceptor buildingAuthorizationInterceptor;

    @Autowired
    private CustomerAuthorizationInterceptor customerAuthorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(buildingAuthorizationInterceptor).addPathPatterns("/admin/building-**").excludePathPatterns("/admin/building-list");
        registry.addInterceptor(customerAuthorizationInterceptor).addPathPatterns("/admin/customer-**").excludePathPatterns("/admin/customer-list");
    }

}
