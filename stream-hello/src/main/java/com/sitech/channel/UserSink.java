package com.sitech.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface UserSink {

    String INPUT = "user";

    @Input(UserSink.INPUT)
    SubscribableChannel user();

} 