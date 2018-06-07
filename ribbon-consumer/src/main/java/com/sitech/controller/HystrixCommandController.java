package com.sitech.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.sitech.command.UserCommand;
import com.sitech.entity.User;
import com.sitech.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HystrixCommandController {

    private static final Logger log = LoggerFactory.getLogger(HystrixCommandController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/usercommand")
    public String command() throws ExecutionException, InterruptedException {
        //请求同步执行
        String u = new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate).execute();
        System.out.println(u + "******************");

        //请求异步执行
        Future<String> future = new UserCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")),restTemplate).queue();

        String str = future.get();

        System.out.println(str + "-------------");

        /*****
         * 通过Observable实现响应式执行方式
         */
        //调用observe(),返回一个Hot Observable
        Observable<String> ho = new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate).observe();

        //调用toObservable(),返回一个Cold Observable
        Observable<String> co = new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("")),restTemplate).toObservable();

        return u + "----" + str + "----" + ho.toString() + "----" + co.toString();

    }

    @Autowired
    private UserService userService;

    @GetMapping("getUserId")
    public String getUserId(@RequestParam String id) throws ExecutionException, InterruptedException {

        User user = userService.getUserById(1L);

        Future<User> userFuture = userService.getUserByIdAsync(1L);

        return user.getName() + "--" + userFuture.get().getName();

    }

    @GetMapping("getUserCacheById")
    public User getUserCacheById(@RequestParam Long id){

        /**
         * 请求缓存操作
         * 在服务请求发起之前，需要先初始化HystrixRequestContext。
         */
        HystrixRequestContext.initializeContext();

        log.info("测试一" +userService.getUserCacheById(1L));
        log.info("测试二" +userService.getUserCacheById(1L));

        User user = new User("李四",15,1L);

        userService.update(user);

        log.info("测试三" +userService.getUserCacheById(1L));

        return userService.getUserCacheById(1L);
    }

} 