package com.sitech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class TraceZipkin1Application {

	private static final Logger log = LoggerFactory.getLogger(TraceZipkin1Application.class);

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@GetMapping(value = "/trace-zipkin-1")
	public String trace(){
		log.info("==========call trace-zipkin-1==========");
		return restTemplate().getForObject("http://trace-zipkin-2/trace-zipkin-2",String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(TraceZipkin1Application.class, args);
	}
}
