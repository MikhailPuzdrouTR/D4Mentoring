package com.mp.d4mentroing.module2.repository;

import com.mp.d4mentroing.module2.TestConfiguration;
import com.mp.d4mentroing.module2.domain.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestConfiguration.class)
@DataJpaTest()
public class EventRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    private Event event1 = Event.builder().id("1").title("Title 1").build();
    private Event event2 = Event.builder().id("2").title("Title 2").build();
    private Event event3 = Event.builder().id("3").title("Title 3").build();
    private Event event4 = Event.builder().id("4").title("Title 1").build();

    @BeforeEach
    void initEach() {
        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);
        eventRepository.save(event4);
    }

    @Test
    void findEventsByTitle_test() {
        var actualResult = eventRepository.findEventsByTitle("Title 1");

        assertEquals(actualResult.size(), 2);
        assertTrue(actualResult.contains(event1));
        assertTrue(actualResult.contains(event4));
    }

    @AfterEach
    void cleanupEach() {
        entityManager.clear();
    }
}
