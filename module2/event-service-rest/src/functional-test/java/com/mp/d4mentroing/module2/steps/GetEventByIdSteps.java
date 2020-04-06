package com.mp.d4mentroing.module2.steps;

import com.mp.d4mentroing.module2.SpringIntegrationTest;
import cucumber.api.java.en.When;
import org.springframework.http.HttpMethod;

public class GetEventByIdSteps extends SpringIntegrationTest {

    @When("^the client gets the event with id (.+)$")
    public void getEventById(String id) throws Throwable {
        var response = restTemplate.exchange(getEventMappingUrl(id), HttpMethod.GET, null, String.class);
        saveEventResponseToContext(response);
    }
}
