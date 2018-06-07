package com.sitech.service;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.sitech.entity.User;
import org.springframework.web.client.RestTemplate;

public class UserGetCommand extends HystrixCommand<User> {

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");

    private RestTemplate restTemplate;

    private Long id;


    public UserGetCommand(RestTemplate restTemplate, Long id) {

        super(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet"))
        .andCommandKey(GETTER_KEY));

        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run(){
        return restTemplate.getForObject("http://USER-SERVICE/user/{1}",User.class,id);
    }


    /**
     * 请求缓存
     * @return
     */
    @Override
    protected String getCacheKey(){
        //根据Id设置缓存
        return String.valueOf(id);
    }

    /**
     * 刷新缓存
     * @param id
     */
    public static void flushCache(Long id) {

        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));

    }



}