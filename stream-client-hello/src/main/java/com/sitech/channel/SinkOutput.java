package com.sitech.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SinkOutput {

    String OUTPUT = "user";

    @Output(SinkOutput.OUTPUT)
    MessageChannel user();

} 