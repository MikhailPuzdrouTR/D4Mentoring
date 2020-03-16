package com.mp.d4mentoring.module1.eventservice.client;

import com.mp.d4mentoring.module1.eventservice.client.service.EventServiceClientImpl;

public class Runner {

    public static void main(String[] args) {
        var eventServiceClient = new EventServiceClientImpl();

        eventServiceClient.printAllEventTopics();
    }
}
