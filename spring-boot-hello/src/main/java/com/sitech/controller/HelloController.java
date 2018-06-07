package com.sitech.controller;

import com.sitech.entity.User;
import com.sitech.service.HelloService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
public class HelloController {


    @Autowired
    private HelloService helloService;

    @ApiOperation(value="Hello World", notes="")
    @GetMapping(path = "/hello")
    public String hello() throws Exception{

        return helloService.hello();

    }

    @ApiOperation(value="获取用户姓名", notes="Hello name")
    @ApiImplicitParam(name = "username", value = "用户姓名", required = true, dataType = "String")
    @GetMapping("helloByName")
    public String helloByName(@RequestParam String name){

        return "Hello" + name;

    }
    @ApiOperation(value="通过Map获取用户姓名", notes="Hello name")
    @ApiImplicitParam(name = "map", value = "用户姓名Map", required = true, dataType = "Map")
    @GetMapping("helloByMap")
    public String helloByMap(@RequestParam Map<String,Object> mapParam){
        return (String) mapParam.get("name");
    }

    @ApiOperation(value = "获取用户姓名年龄",notes = "Hello name,age")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "username",value = "用户姓名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "userage" , value = "用户年龄" ,required = true,dataType = "String")}
    )
    @GetMapping("helloByNameAge")
    public User helloByNameAge(@RequestHeader String name,@RequestHeader int age){

        return new User(name,age);

    }

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    @ApiImplicitParam(name = "user",value = "用户", required = true,dataType = "User")
    @PostMapping("helloByUser")
    public String helloByUser(@RequestBody User user){

        return user.getName() + ":" + user.getAge();

    }

    @ApiOperation(value = "通过ID获取用户信息",notes = "通过ID获取用户信息")
    @ApiImplicitParam(name = "userId",value = "用户标识", required = true,dataType = "Long")
    @GetMapping("userById")
    public User userById(@RequestParam Long id){

        return new User("张三11",15,id);

    }







}