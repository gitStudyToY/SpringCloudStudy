package com.sitech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HelloService {

    private Logger log = LoggerFactory.getLogger(HelloService.class);



    @Autowired
    private DiscoveryClient discoveryClient;

    public String hello() throws Exception{

        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();

        int sleepTime = new Random().nextInt(3000);
        log.info("SleepTime:" + sleepTime);

        Thread.sleep(sleepTime);


        log.info("/hello,host" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ",serviceId:" + serviceInstance.getServiceId());

        return "Hello World!";
    }

} 