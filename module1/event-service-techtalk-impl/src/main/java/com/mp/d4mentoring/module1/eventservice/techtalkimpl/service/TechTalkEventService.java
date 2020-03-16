package com.mp.d4mentoring.module1.eventservice.techtalkimpl.service;

import com.mp.d4mentoring.module1.eventservice.api.service.EventService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TechTalkEventService implements EventService {

    private static String TECH_TALK_TOPIC_PREFIX = "TechTalk:";

    @Override
    public List<String> getEvents() {
        return Stream.of("Java 10 and 11", "TDD", "Leadership Skills")
                .map(this::addPrefix)
                .collect(Collectors.toUnmodifiableList());
    }

    private String addPrefix(String topic) {
        return String.format("%s %s", TECH_TALK_TOPIC_PREFIX, topic);
    }
}
