package com.sitech.receiver;

import com.sitech.sender.SinkSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

//@EnableBinding(value = {Sink.class,SinkSender.class})
@EnableBinding(value = {Sink.class, SinkSender.class})
public class SendReceiver {

    private static final Logger logger = LoggerFactory.getLogger(SendReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        logger.info("Received:" + payload);
    }

}