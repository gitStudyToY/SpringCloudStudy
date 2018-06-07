package com.sitech.inter;

import com.sitech.entity.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/refactor")
public interface HelloService {
    @GetMapping("/hello1")
    String hello();

    @GetMapping("helloByName1")
    public String helloByName(@RequestParam("name") String name);

    /**
     * @RequestParam、@RequestHeader的参数值不可少，否则会抛IllegalStateException异常、value属性不能为空
     * @param name
     * @param age
     * @return
     */
    @GetMapping("helloByNameAge1")
    public User helloByNameAge(@RequestHeader("name") String name, @RequestHeader("age") int age);

    @PostMapping("helloByUser1")
    public String helloByUser(@RequestBody User user);



} 