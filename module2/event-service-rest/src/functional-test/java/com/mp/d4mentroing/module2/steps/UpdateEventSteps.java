package com.mp.d4mentroing.module2.steps;

import com.mp.d4mentroing.module2.SpringIntegrationTest;
import com.mp.d4mentroing.module2.domain.Event;
import cucumber.api.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;

public class UpdateEventSteps extends SpringIntegrationTest {

    @When("^the client updates the event (.+) with new title (.+)$")
    public void updateEvent(String eventId, String newTitle) throws MalformedURLException  {
        Event eventForUpdate = Event.builder().title(newTitle).build();
        HttpEntity<Event> httpEntity = new HttpEntity<>(eventForUpdate);
        ResponseEntity<String> response = restTemplate.exchange(getEventMappingUrl(eventId), HttpMethod.PUT, httpEntity, String.class);
        saveEventResponseToContext(response);
    }
}
