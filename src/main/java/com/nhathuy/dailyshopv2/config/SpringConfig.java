package com.nhathuy.dailyshopv2.config;

import com.nhathuy.dailyshopv2.interceptor.MenuHandleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SpringConfig implements WebMvcConfigurer {

	@Autowired
	MenuHandleInterceptor menuHandleInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(menuHandleInterceptor);
	}
}
