package com.sitech.sender;

import com.sitech.channel.SendProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.Date;

//@EnableBinding(value = {SendProcessor.class, Processor.class})
@EnableBinding(value = {SendProcessor.class})
public class App2 {

    private static final Logger log = LoggerFactory.getLogger(App2.class);

    @Bean
    @InboundChannelAdapter(value = SendProcessor.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> MessageSource(){
        return () -> new GenericMessage<Date>( new Date());
    }

    @StreamListener(SendProcessor.INPUT)
    public void receiveFromOutput(Object payload) {
        log.info("Received：" + payload);
    }

//    @StreamListener(Processor.INPUT)
//    public void receiveFromRxjavaOutput(Object payload) {
//        log.info("Received：" + payload);
//    }

} 