package com.mp.d4mentroing.module2;

import com.mp.d4mentroing.module2.repository.EventRepository;
import cucumber.api.java.After;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks extends SpringIntegrationTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventTestContext eventTestContext;

    @After
    public void afterScenario() {
        eventRepository.deleteAll();

        eventTestContext.setRespondedEvent(null);
        eventTestContext.setHttpStatus(null);
    }
}
