package com.mp.d4mentroing.module2.steps;

import com.mp.d4mentroing.module2.TestConfiguration;
import com.mp.d4mentroing.module2.domain.Event;
import com.mp.d4mentroing.module2.domain.EventType;
import com.mp.d4mentroing.module2.repository.EventRepository;
import com.mp.d4mentroing.module2.service.EventService;
import com.mp.d4mentroing.module2.serviceimpl.EventServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {TestConfiguration.class, EventController.class, EventService.class, EventRepository.class, EventServiceImpl.class, OptionalResponseControllerAdvice.class})
public class EventControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Value("${'events-service.endpoints.events'}")
    private String eventsEndpoint;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EventRepository eventRepository;

    private String title1 = "Title-1";

    private Event event1 = Event.builder().id("1").title(title1).build();
    private Event event2 = Event.builder().id("2").title("Title-2").build();
    private Event event3 = Event.builder().id("3").title("Title-3").build();
    private Event event4 = Event.builder().id("4").title(title1).build();

    private ParameterizedTypeReference<List<Event>> eventsListType = new ParameterizedTypeReference<List<Event>>() {};

    @BeforeEach
    void beforeEach() {
        eventRepository.saveAll(List.of(event1, event2, event3, event4));
    }

    @AfterEach
    void afterEach() {
        eventRepository.deleteAll();
    }

    @Test
    public void getAllEvents_test() throws MalformedURLException {
        List<Event> expectedEvents = List.of(event1, event2, event3, event4);

        ResponseEntity<List<Event>> actualResponse = restTemplate.exchange(getEventMappingUrl(""), HttpMethod.GET, null, eventsListType);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(actualResponse.getBody().stream().sorted(comparing(Event::getId)).collect(Collectors.toList()), expectedEvents);
    }

    @Test
    public void getAllEvents_findByTitle_test() throws MalformedURLException {
        List<Event> expectedEvents = List.of(event1, event4);

        ResponseEntity<List<Event>> actualResponse = restTemplate.exchange(getEventMappingUrl("", Map.of("title", title1)), HttpMethod.GET, null, eventsListType);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(actualResponse.getBody().stream().sorted(comparing(Event::getId)).collect(Collectors.toList()), expectedEvents);
    }

    @Test
    public void getById_eventFound_test() throws MalformedURLException {
        ResponseEntity<Event> actualResponse = restTemplate.getForEntity(getEventMappingUrl("/" + event2.getId()), Event.class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(event2, actualResponse.getBody());
    }

    @Test
    public void getById_eventNotFound_test() throws MalformedURLException {
        ResponseEntity actualResponse = restTemplate.getForEntity(getEventMappingUrl("/25"), String.class);

        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    @Test
    public void createEvent_test() throws MalformedURLException {
        var newEvent = Event.builder().title("New Event For NTMS. Party in new flat").place("Tatsiana's flat").speaker("Tatsiana Larkina").eventType(EventType.PARTY).build();
        HttpEntity<Event> httpEntity = new HttpEntity<>(newEvent);

        ResponseEntity<Event> actualResponse = restTemplate.exchange(getEventMappingUrl(""), HttpMethod.POST, httpEntity, Event.class);

        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertNotNull(actualResponse.getBody().getId());
        assertEquals(newEvent.getEventType(), actualResponse.getBody().getEventType());
        assertEquals(newEvent.getPlace(), actualResponse.getBody().getPlace());
        assertEquals(newEvent.getTitle(), actualResponse.getBody().getTitle());
        assertEquals(newEvent.getSpeaker(), actualResponse.getBody().getSpeaker());
    }

    @Test
    public void updateEvent_positiveTest() throws MalformedURLException {
        var eventForUpdate = Event.builder().id(event3.getId()).title("New Title").place(event3.getPlace()).build();
        HttpEntity<Event> httpEntity = new HttpEntity<>(eventForUpdate);

        ResponseEntity<Event> actualResponse = restTemplate.exchange(getEventMappingUrl("/" + eventForUpdate.getId()), HttpMethod.PUT, httpEntity, Event.class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(eventForUpdate, actualResponse.getBody());

        Optional<Event> foundInDbEvent = eventRepository.findById(event3.getId());
        assertTrue(foundInDbEvent.isPresent());
        assertEquals(eventForUpdate, foundInDbEvent.get());
    }

    @Test
    public void updateEvent_eventNotFoundInSystem_negativeTest() throws MalformedURLException {
        var eventForUpdate = Event.builder().id("Random Id").title("New Title").place(event3.getPlace()).build();
        HttpEntity<Event> httpEntity = new HttpEntity<>(eventForUpdate);

        ResponseEntity<String> actualResponse = restTemplate.exchange(getEventMappingUrl("/" + eventForUpdate.getId()), HttpMethod.PUT, httpEntity, String.class);
        Optional<Event> foundInDbEvent = eventRepository.findById(event3.getId());

        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
        assertTrue(foundInDbEvent.isPresent());
        assertEquals(event3, foundInDbEvent.get());
    }

    @Test
    public void deleteEvent_eventExists_test() throws MalformedURLException {
        ResponseEntity<Event> actualResponse = restTemplate.exchange(getEventMappingUrl("/" + event4.getId()), HttpMethod.DELETE, null, Event.class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(event4, actualResponse.getBody());

        assertTrue(eventRepository.findById(event4.getId()).isEmpty());
    }

    @Test
    public void deleteEvent_eventNotExists_test() throws MalformedURLException {
        ResponseEntity<String> actualResponse = restTemplate.exchange(getEventMappingUrl("/RandomId"), HttpMethod.DELETE, null, String.class);

        assertEquals(HttpStatus.NOT_FOUND, actualResponse.getStatusCode());
    }

    private String getEventMappingUrl(String subMapping) throws MalformedURLException {
        return getEventMappingUrl(subMapping, Map.of());
    }

    private String getEventMappingUrl(String subMapping, Map<String, String> params) throws MalformedURLException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(new URL(String.format(eventsEndpoint, port, subMapping)).toString());

        for (String key : params.keySet()) {
            builder = builder.queryParam(key, params.get(key));
        }

        return builder.toUriString();
    }
}
