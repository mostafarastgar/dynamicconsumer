package com.isc.npsd.kafa.messegeproducer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface HassanSource {

    /**
     * Name of the output channel.
     */
    String OUTPUT = "hassanOut";
    /**
     * @return output channel
     */
    @Output(OUTPUT)
    MessageChannel output();

}
