package com.mp.d4mentroing.module2.steps;

import com.mp.d4mentroing.module2.SpringIntegrationTest;
import cucumber.api.java.en.When;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;

public class DeleteEventSteps extends SpringIntegrationTest {

    @When("^the client deletes event with (.+) id$")
    public void deleteEventById(String id) throws MalformedURLException {
        ResponseEntity<String> response = restTemplate.exchange(getEventMappingUrl(id), HttpMethod.DELETE, null, String.class);
        saveEventResponseToContext(response);
    }
}
