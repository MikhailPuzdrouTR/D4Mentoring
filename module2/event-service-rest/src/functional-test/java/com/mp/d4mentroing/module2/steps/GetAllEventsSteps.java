package com.mp.d4mentroing.module2.steps;

import com.mp.d4mentroing.module2.SpringIntegrationTest;
import com.mp.d4mentroing.module2.domain.Event;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


public class GetAllEventsSteps extends SpringIntegrationTest {

    private ParameterizedTypeReference<List<Event>> eventsListType = new ParameterizedTypeReference<List<Event>>() {};

    private ResponseEntity<List<Event>> response;


    @When("^the client calls /events with title '(.*)'$")
    public void theClientCallsEvents(String title) throws Throwable {
        response = restTemplate.exchange(getEventMappingUrl("", Map.of("title", title)), HttpMethod.GET, null, eventsListType);
        eventTestContext.setHttpStatus(response.getStatusCode());
    }

    @And("^the size of responsed events if (\\d+)$")
    public void sizeOfRespondedEntitiesMustBe(int sizeOfEntities) {
        assertEquals(sizeOfEntities, response.getBody().size());
    }

    @And("^the response have to contain entity with id (.*)$")
    public void verirfyEntityInResponseById(String id) {
        var filtered = response.getBody().stream().filter(event -> event.getId().equals(id)).collect(Collectors.toList());
        assertEquals(1, filtered.size() );
    }
}
