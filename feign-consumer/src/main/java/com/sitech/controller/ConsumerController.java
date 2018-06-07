package com.sitech.controller;

import com.sitech.entity.User;
import com.sitech.inter.HelloService2;
import com.sitech.inter.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    HelloService2 helloService;

    @Autowired
    RefactorHelloService refactorHelloService;

    @GetMapping("/feign-consumer")
    public String helloConsumer(){
        return helloService.hello();
    }

    @GetMapping("/feign-consumer2")
    public String helloConsumer2(){
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello() + "\n");
        sb.append(helloService.helloByName("test") + "\n");
        sb.append(helloService.helloByNameAge("test",15) + "\n");
        sb.append(helloService.helloByUser(new User("test",18)));

        return sb.toString();
    }

    @GetMapping("/feign-consumer3")
    public String helloConsumer3(){
        StringBuilder sb = new StringBuilder();

        sb.append(refactorHelloService.hello() + "\n");
        sb.append(refactorHelloService.helloByName("test") + "\n");
        sb.append(refactorHelloService.helloByNameAge("test",15) + "\n");
        sb.append(refactorHelloService.helloByUser(new User("test",18)));

        return sb.toString();
    }


} 