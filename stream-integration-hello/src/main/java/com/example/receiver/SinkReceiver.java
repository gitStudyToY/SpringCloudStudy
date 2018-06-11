package com.example.receiver;

import com.example.channel.UserSink;
import com.example.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@EnableBinding(value = {Sink.class,UserSink.class})
public class SinkReceiver {

    private static final Logger log = LoggerFactory.getLogger(SinkReceiver.class);

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void receive(Object payload){
        log.info("Received:" + payload);
    }

    @Transformer(inputChannel = Sink.INPUT,outputChannel = Sink.INPUT)
    public Object transform(Date message){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message);
    }

    @ServiceActivator(inputChannel = UserSink.INPUT)
    public void receive(User user){
        log.info("Received:" + user.getName());
    }

    @Transformer(inputChannel = UserSink.INPUT,outputChannel = UserSink.INPUT)
    public User transform(String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(message,User.class);

        return user;
    }


}