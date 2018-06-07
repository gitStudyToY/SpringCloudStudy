package com.sitech;

import com.netflix.zuul.FilterProcessor;
import com.sitech.filter.AccessFilter;
import com.sitech.filter.DidiErrorAttributes;
import com.sitech.filter.DidiFilterProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
		//使过滤器生效
		FilterProcessor.setProcessor(new DidiFilterProcessor());
	}

	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}

	@Bean
	public PatternServiceRouteMapper serviceRouteMapper(){
		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)","${version}/${name}");
	}

	@Bean
	public DidiErrorAttributes errorAttributes(){
		return new DidiErrorAttributes();
	}



}
