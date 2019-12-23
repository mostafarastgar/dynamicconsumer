package com.isc.npsd.kafa.messegeproducer.dynamic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

public class CustomAckMessageListener implements AcknowledgingMessageListener<String, String> {

    @Autowired
    private TestService testService;

    @Override
    public void onMessage(
            ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {

        // process message
//        testService.test(consumerRecord.key(), consumerRecord.value(), senderBic);

        // commit offset
        acknowledgment.acknowledge();
    }
}