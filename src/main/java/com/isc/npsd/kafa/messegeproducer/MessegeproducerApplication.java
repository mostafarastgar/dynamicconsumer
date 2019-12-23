package com.isc.npsd.kafa.messegeproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding({Source.class, HassanSource.class})
public class MessegeproducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessegeproducerApplication.class, args);
    }

}

