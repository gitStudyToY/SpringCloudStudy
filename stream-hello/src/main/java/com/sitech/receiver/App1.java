package com.sitech.receiver;

import com.sitech.channel.SendProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(SendProcessor.class)
public class App1 {

    private static final Logger log = LoggerFactory.getLogger(App1.class);

    @StreamListener(SendProcessor.INPUT)
    @SendTo(SendProcessor.OUTPUT)
    public Object receiveFormInput(Object payload){
        log.info("Received：" + payload);
        return "Form Input Channel Return - " + payload;
    }

} 