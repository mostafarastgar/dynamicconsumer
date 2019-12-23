package com.isc.npsd.kafa.messegeproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.core.env.Environment;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

//@MessageEndpoint
class MessageListener {

//    @ServiceActivator(inputChannel = Sink.INPUT)
    public void handleGreetings(@Payload String message, @Header("message_type") String messageType, @Header("tx_id") String txId) {
        System.out.println(messageType);
        System.out.println(txId);
        System.out.println(message);
    }
}