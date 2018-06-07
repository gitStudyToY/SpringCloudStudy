package com.sitech.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sitech.inter.ConsumerInter;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService implements ConsumerInter {

    @HystrixCommand
    public String helloConsumer(){
        //return restTemplate.getForEntity("http://hello-service/hello",String.class).getBody();
        // return helloService.helloService();

        return "ribbon-consumer";
    }

} 