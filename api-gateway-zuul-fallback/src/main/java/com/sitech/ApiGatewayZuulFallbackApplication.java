package com.sitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayZuulFallbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayZuulFallbackApplication.class, args);
	}
}
