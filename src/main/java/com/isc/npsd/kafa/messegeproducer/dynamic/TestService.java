package com.isc.npsd.kafa.messegeproducer.dynamic;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public void test(String key, String value, String senderBic) {
        System.out.println("************ " + key + " " + value + " " + senderBic + "**************");
    }
}
