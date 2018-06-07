package com.sitech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class Trace1Application {

	private static final Logger log = LoggerFactory.getLogger(Trace1Application.class);

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@GetMapping(value = "/trace-1")
	public String trace(){
		log.info("==========call trace-1==========");
		return restTemplate().getForObject("http://trace-2/trace-2",String.class);
	}

//	@Bean
//	public AlwaysSampler alwaysSampler(){
//		return new AlwaysSampler();
//	}


	public static void main(String[] args) {
		SpringApplication.run(Trace1Application.class, args);
	}
}
