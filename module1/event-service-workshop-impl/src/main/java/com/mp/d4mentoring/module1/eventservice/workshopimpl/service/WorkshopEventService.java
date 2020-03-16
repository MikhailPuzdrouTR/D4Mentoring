package com.mp.d4mentoring.module1.eventservice.workshopimpl.service;

import com.mp.d4mentoring.module1.eventservice.api.service.EventService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkshopEventService implements EventService {

    private static final String WORKSHOP_TOPIC_PREFIX = "Workshop:";

    public List<String> getEvents() {
        return Stream.of("AWS Migration. How and Why",
                "Jenkins and Spinnaker",
                "How to Fly with WebFlux on Spring 5")
                .map(WorkshopEventService::addPrefix)
                .collect(Collectors.toUnmodifiableList());
    }

    private static String addPrefix(String topic) {
        return String.format("%s %s", WORKSHOP_TOPIC_PREFIX, topic);
    }
}
