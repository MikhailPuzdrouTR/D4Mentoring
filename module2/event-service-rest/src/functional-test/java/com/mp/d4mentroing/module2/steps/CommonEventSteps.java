package com.mp.d4mentroing.module2.steps;

import com.mp.d4mentroing.module2.SpringIntegrationTest;
import com.mp.d4mentroing.module2.domain.Event;
import com.mp.d4mentroing.module2.repository.EventRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommonEventSteps extends SpringIntegrationTest {

    @Autowired
    private EventRepository eventRepository;

    @Given("^the event with id (.+) and title (.+)$")
    public void givenEvent(String id, String title) {
        eventRepository.save(Event.builder().id(id).title(title).build());
    }


    @Then("^the client recieves status code of (\\d+)$")
    public void clientRecievesStatusCode(int statusCode) {
        assertNotNull(eventTestContext.getHttpStatus());
        assertEquals(statusCode, eventTestContext.getHttpStatus().value());
    }

    @And("^the entity with title (.+) title exists$")
    public void verifyEntityExistsByTitle(String title) {
        assertFalse(eventRepository.findEventsByTitle(title).isEmpty());
    }

    @And("^responded entity has (.+) id and (.+) title$")
    public void verifyEvent(String id, String title) {
        var respondedEvent = eventTestContext.getRespondedEvent();
        Assertions.assertNotNull(respondedEvent);
        Assertions.assertEquals(respondedEvent.getTitle(), title);
        Assertions.assertEquals(respondedEvent.getId(), id);
    }
}
