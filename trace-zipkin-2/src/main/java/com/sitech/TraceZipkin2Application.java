package com.sitech;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class TraceZipkin2Application {

	private static final Logger log = LoggerFactory.getLogger(TraceZipkin2Application.class);

	@GetMapping("trace-zipkin-2")
	public String trace(HttpServletRequest request){
		log.info("=======<call trace-zipkin-2,TraceId={},SpanId={}>=========" , request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
		return "Trace";
	}

	public static void main(String[] args) {
		SpringApplication.run(TraceZipkin2Application.class, args);
	}
}
