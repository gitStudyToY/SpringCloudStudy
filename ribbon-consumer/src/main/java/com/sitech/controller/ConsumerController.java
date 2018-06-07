package com.sitech.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sitech.command.UserCommand;
import com.sitech.entity.User;
import com.sitech.service.ConsumerService;
import com.sitech.service.HelloService;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class ConsumerController {

    private static final Logger log = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;

    @Autowired
    private ConsumerService consumerService;

    @GetMapping(path = "/ribbon-consumer")
    public String helloConsumer(){
        //return restTemplate.getForEntity("http://hello-service/hello",String.class).getBody();
        return helloService.helloService();

        //return consumerService.helloConsumer();
    }

} 