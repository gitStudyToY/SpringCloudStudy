package com.example.receiver;

import com.example.channel.SendProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBinding(SendProcessor.class)
public class App1 {

    private static final Logger log = LoggerFactory.getLogger(App1.class);

    @ServiceActivator(inputChannel = SendProcessor.INPUT,outputChannel = SendProcessor.OUTPUT)
    public Object receiveFromInput(Object payload) {
        log.info("Received:" + payload);

        return "From Input Channel Return - " + payload;
    }

} 