package com.sitech.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sitech.entity.User;
import org.springframework.web.client.RestTemplate;

public class UserPostCommand extends HystrixCommand<User>{

    private RestTemplate restTemplate;

    private User user;

    public UserPostCommand(RestTemplate restTemplate, User user) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet")));
        this.restTemplate = restTemplate;
        this.user = user;
    }

    @Override
    protected User run(){
        //写操作
        User u = restTemplate.postForObject("http://USER-SERVICE/user",user,User.class);
        //刷新缓存
        UserGetCommand.flushCache(u.getId());

        return  u;
    }
}