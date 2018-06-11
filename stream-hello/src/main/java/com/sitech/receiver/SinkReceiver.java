package com.sitech.receiver;

import com.sitech.channel.UserSink;
import com.sitech.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(value = {Sink.class, UserSink.class})
public class SinkReceiver {

    private static final Logger log = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        log.info("Received:" + payload);
    }

    @StreamListener(UserSink.INPUT)
    public void receive(User user){
        log.info("Received:" + user.getName() + "---" + user.getAge());
    }


}