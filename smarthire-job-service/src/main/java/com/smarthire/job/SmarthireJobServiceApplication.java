package com.smarthire.job;

import org.springframework.boot.SpringApplication;

import org.springframework.core.Ordered;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.RequestContextFilter;

@SpringBootApplication
@EnableFeignClients
public class SmarthireJobServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmarthireJobServiceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<RequestContextFilter> requestContextFilter() {

	    FilterRegistrationBean<RequestContextFilter> filter =
	            new FilterRegistrationBean<>();

	    filter.setFilter(new RequestContextFilter());
	    filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

	    return filter;
	}
}


