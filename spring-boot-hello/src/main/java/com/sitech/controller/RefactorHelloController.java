package com.sitech.controller;

import com.sitech.entity.User;
import com.sitech.inter.HelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorHelloController implements HelloService {
    @Override
    public String hello() {
        return "Hello World";
    }

    @Override
    public String helloByName(@RequestParam("name") String name) {
        return name;
    }

    /**
     * @param name
     * @param age
     * @return
     * @RequestParam、@RequestHeader的参数值不可少，否则会抛IllegalStateException异常、value属性不能为空
     */
    @Override
    public User helloByNameAge(@RequestHeader("name") String name, @RequestHeader("age") int age) {
        return new User(name,age);
    }

    @Override
    public String helloByUser(@RequestBody User user) {
        return user.getName();
    }
}