package com.sitech.service;

import com.netflix.hystrix.HystrixCommand;
import com.sitech.entity.User;

import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

public class UserBatchCommand extends HystrixCommand<List<User>> {

    com.sitech.inter.UserService userService;
    List<Long> userIds;

    public UserBatchCommand(com.sitech.inter.UserService userService, List<Long> userIds) {
        super(Setter.withGroupKey(asKey("userServiceCommand")));
        this.userService = userService;
        this.userIds = userIds;
    }

    @Override
    protected List<User> run() throws Exception {

        return userService.findAll(userIds);

    }
}