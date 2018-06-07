package com.sitech.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    private Logger log = LoggerFactory.getLogger(HelloService.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 利用线程模仿服务阻塞
     * Hystrix的默认超时时间为2000毫秒
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloFallBack",commandKey = "hellokey")
    public String helloService(){

        long start = System.currentTimeMillis();

        String result = restTemplate.getForEntity("http://hello-service/hello",String.class).getBody();

        long end = System.currentTimeMillis();

        log.info("Spend Time:" + (end - start));

        return result;
    }

    public String helloFallBack(){
        return "error";
    }

} 