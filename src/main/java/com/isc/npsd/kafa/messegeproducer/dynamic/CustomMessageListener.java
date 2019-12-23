package com.isc.npsd.kafa.messegeproducer.dynamic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class CustomMessageListener implements MessageListener<String, String> {

    @Autowired
    private TestService testService;

    private String senderBic;

    @Override
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {

        // process message
        testService.test(consumerRecord.key(), consumerRecord.value(), senderBic);
    }

    public String getSenderBic() {
        return senderBic;
    }

    public void setSenderBic(String senderBic) {
        this.senderBic = senderBic;
    }
}