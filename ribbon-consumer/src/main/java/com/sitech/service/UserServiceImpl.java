package com.sitech.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sitech.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements com.sitech.inter.UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCollapser(batchMethod = "findAll",collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds",value = "100")})
    public User find(Long id) {
        return restTemplate.getForObject("http://USER-SERVICE/users/{1}",User.class,id);
    }

    @Override
    @HystrixCommand
    public List<User> findAll(List<Long> ids) {
        return restTemplate.getForObject("http://USER-SERVICE/users?ids={1}",List.class, StringUtils.join(ids,","));
    }
}