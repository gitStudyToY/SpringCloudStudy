package com.sitech.service;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.sitech.entity.User;
import org.springframework.web.client.RestTemplate;


public class UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;

    private Long id;

    public UserCommand(){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("groupName"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CommandName"))
        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey")));
    }

    public UserCommand(RestTemplate restTemplate, Long id, com.netflix.hystrix.HystrixCommand.Setter setter) {
        super(setter);

        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run(){
        return restTemplate.getForObject("http://USER-SERVICE/user/{1}",User.class,id);
    }

    @Override
    protected User getFallback(){
        return new User();
    }

    @Override
    protected String getCacheKey(){
        //根据Id设置缓存
        return String.valueOf(id);
    }


}