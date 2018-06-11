package com.sitech.sender;

import com.sitech.channel.SinkOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Date;


@EnableBinding(value = {SinkSender.SinkOutput.class, SinkOutput.class})
public class SinkSender {

    private static final Logger log = LoggerFactory.getLogger(SinkSender.class);

    @Bean
    @InboundChannelAdapter(value = SinkOutput.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> timerMessageSource(){

        System.out.println("Sender------------------");
        return () -> new GenericMessage<>(new Date());
    }

    public interface SinkOutput{
        String OUTPUT = "input";

        @Output(SinkOutput.OUTPUT)
        MessageChannel output();
    }

    @Bean
    @InboundChannelAdapter(value = com.sitech.channel.SinkOutput.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> userMessageSource(){


        return () -> new GenericMessage<>("{\"name\":\"didi\",\"age\":15,\"id\":122}");
    }

}