package com.mp.d4mentroing.module2.steps;

import com.mp.d4mentroing.module2.SpringIntegrationTest;
import com.mp.d4mentroing.module2.domain.Event;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class CreateEventSteps extends SpringIntegrationTest {

    private ResponseEntity<Event> createEventResponse;

    @When("^the client creates an event with title (.+)$")
    public void createEventWithIdAndTitle(String title) throws Throwable {
        HttpEntity<Event> httpEntity = new HttpEntity<>(Event.builder().title(title).build());
        createEventResponse = restTemplate.exchange(getEventMappingUrl(), HttpMethod.POST, httpEntity, Event.class);
        eventTestContext.setHttpStatus(createEventResponse.getStatusCode());
    }

    @And("^the created entity must have generated id$")
    public void checkGeneratedId() {
        assertNotNull(createEventResponse.getBody());
        assertNotNull(createEventResponse.getBody().getId());
        assertFalse(createEventResponse.getBody().getId().isBlank());
    }
}
