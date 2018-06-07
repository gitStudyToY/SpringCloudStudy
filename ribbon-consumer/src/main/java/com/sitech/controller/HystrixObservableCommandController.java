package com.sitech.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.sitech.command.UserObservableCommand;
import com.sitech.entity.User;
import com.sitech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

@RestController
public class HystrixObservableCommandController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("getUserName")
    public String getUserName(@RequestParam Long id) {
        Observable<User> userObservable = new  UserObservableCommand(HystrixObservableCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate,id).observe();

        Observable<User> userObservable2 = new  UserObservableCommand(HystrixObservableCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate,id).toObservable();


        Observable<User> u = userService.getUserByIdObservable(1L);

        return "";
    }


} 