package com.example.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.rxjava.EnableRxJavaProcessor;
import org.springframework.cloud.stream.annotation.rxjava.RxJavaProcessor;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@EnableRxJavaProcessor
public class App1 {

    private static final Logger log = LoggerFactory.getLogger(App1.class);

    @Bean
    public RxJavaProcessor<Date,String> processor(){
        return inputStream -> inputStream.map(data -> {
            log.info("Received : " + data);
            return data;
        }).buffer(5).map(data -> String.valueOf("From Input Channel Return - " + data));
    }

} 