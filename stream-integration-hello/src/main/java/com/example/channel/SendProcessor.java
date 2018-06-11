package com.example.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface SendProcessor {

    String INPUT = "send";

    @Input(SendProcessor.INPUT)
    SubscribableChannel send();

    String OUTPUT = "sendOutput";

    @Output(SendProcessor.OUTPUT)
    MessageChannel sendOutput();


} 