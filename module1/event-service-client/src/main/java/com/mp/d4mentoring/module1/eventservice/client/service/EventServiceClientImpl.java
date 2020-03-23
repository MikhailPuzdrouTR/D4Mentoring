package com.mp.d4mentoring.module1.eventservice.client.service;

import com.mp.d4mentoring.module1.eventservice.api.service.EventService;
import com.mp.d4mentoring.module1.eventservice.client.service.markdown.MarkdownService;
import com.mp.d4mentoring.module1.eventservice.client.service.markdown.MarkdownServiceImpl;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class EventServiceClientImpl implements EventServiceClient {

    private MarkdownService markdownService = new MarkdownServiceImpl();

    @Override
    public void printAllEventTopics() {
        System.out.println("Available Topics:");
        markdownService.formatList(getAllEventTopic()).forEach(System.out::println);
        System.out.println("---------------------------------\n");
    }

    private List<String> getAllEventTopic() {
        return loadEventServices().stream()
                .flatMap(eventService -> eventService.getEvents().stream())
                .collect(Collectors.toUnmodifiableList());
    }

    private List<EventService> loadEventServices() {
        return ServiceLoader.load(EventService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toUnmodifiableList());
    }
}
