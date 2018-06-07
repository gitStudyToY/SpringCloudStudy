package com.sitech.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sitech.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserCommandTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {

        //请求同步执行
//        String u = new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(
//                HystrixCommandGroupKey.Factory.asKey("")),restTemplate).execute();
//
//        System.out.println(u + "******************");
    }

} 