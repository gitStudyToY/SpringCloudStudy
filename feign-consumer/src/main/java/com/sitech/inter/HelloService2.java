package com.sitech.inter;

import com.sitech.config.DisableHystrixConfiguration;
import com.sitech.config.FullLogConfiguration;
import com.sitech.entity.User;
import com.sitech.fallback.HelloServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "hello-service",configuration = {DisableHystrixConfiguration.class,FullLogConfiguration.class},fallback = HelloServiceFallback.class)
public interface HelloService2 {
    @GetMapping("/hello")
    String hello();

    @GetMapping("helloByName")
    public String helloByName(@RequestParam("name")String name);

    /**
     * @RequestParam、@RequestHeader的参数值不可少，否则会抛IllegalStateException异常、value属性不能为空
     * @param name
     * @param age
     * @return
     */
    @GetMapping("helloByNameAge")
    public User helloByNameAge(@RequestHeader("name") String name, @RequestHeader("age") int age);

    @PostMapping("helloByUser")
    public String helloByUser(@RequestBody User user);



} 