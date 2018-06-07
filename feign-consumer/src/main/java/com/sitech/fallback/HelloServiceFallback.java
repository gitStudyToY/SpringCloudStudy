package com.sitech.fallback;

import com.sitech.entity.User;
import com.sitech.inter.HelloService2;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloServiceFallback implements HelloService2 {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String helloByName(@RequestParam("name") String name) {
        return "error";
    }

    /**
     * @param name
     * @param age
     * @return
     * @RequestParam、@RequestHeader的参数值不可少，否则会抛IllegalStateException异常、value属性不能为空
     */
    @Override
    public User helloByNameAge(@RequestHeader("name") String name, @RequestHeader("age") int age) {
        return new User();
    }

    @Override
    public String helloByUser(@RequestBody User user) {
        return "error";
    }
}