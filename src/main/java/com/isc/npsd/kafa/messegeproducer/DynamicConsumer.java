package com.isc.npsd.kafa.messegeproducer;

import com.isc.npsd.kafa.messegeproducer.dynamic.CustomMessageListener;
import com.isc.npsd.kafa.messegeproducer.dynamic.KafkaConsumerUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;

@Service
public class DynamicConsumer {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Environment env;

    @PreDestroy
    public void destroy() {
        KafkaConsumerUtil.stopAll();
    }

    @PostConstruct
    public void init() {
        String value = env.getProperty("consumers-info." + env.getProperty("active-consumer-profile"));
        String[] consumersData = value.split(",");
        for (String consumerData : consumersData) {
            String[] info = consumerData.split(":");
            CustomMessageListener customMessageListener = applicationContext.getBean(CustomMessageListener.class);
            customMessageListener.setSenderBic(info[1]);
            KafkaConsumerUtil.startOrCreateConsumers(info[0], customMessageListener, Integer.valueOf(info[2]), new HashMap<>() {{
                put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.160.9:9092");
                put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, false);
                put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
                put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
                put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
                put(ConsumerConfig.GROUP_ID_CONFIG, info[1] + "_*consumer");
                put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
                put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            }});
        }
    }
}
