package com.mp.d4mentroing.module2.serviceimpl;

import com.mp.d4mentroing.module2.domain.Event;
import com.mp.d4mentroing.module2.repository.EventRepository;
import com.mp.d4mentroing.module2.service.EventService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {EventService.class, EventRepository.class})
@SpringBootTest
public class EventServiceTests {

    private static final String TITLE = "NTMS is cool!";
    private static final String PLACE = "Z29";
    private static final String ID = "ID";
    private static final Event EVENT = Event.builder().id(ID).title(TITLE).place(PLACE).build();

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService = new EventServiceImpl();

    @Test
    public void getAllEvents_test() {
        var expectedResult = List.of(
                Event.builder().id("1").title("Event 1").build(),
                Event.builder().id("2").title("Event 2").build(),
                Event.builder().id("3").title("Event 3").build()
        );

        when(eventRepository.findAll()).thenReturn(expectedResult);

        var actualResult = eventService.getAllEvents();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void createEvent_test() {
        var newEvent = Event.builder().title(TITLE).place(PLACE).build();

        when(eventRepository.save(newEvent)).thenReturn(EVENT);

        var actualResult = eventService.createEvent(newEvent);

        assertEquals(EVENT, actualResult);
    }

    @Test
    public void updateEvent_positiveTest() {
        var eventIsSystem = Optional.of(Event.builder().id(ID).title("Old title").build());

        doReturn(eventIsSystem).when(eventRepository).findById(ID);
        doReturn(EVENT).when(eventRepository).save(EVENT);

        var actualResult = eventService.updateEvent(EVENT);

        assertEquals(EVENT, actualResult);
    }

    @Test
    public void updateEvent_idIsNull_negativeTest() {
        var expectedMessage = "Event not exists or id is empty";

        var actualException = assertThrows(RuntimeException.class, () -> eventService.updateEvent(EVENT));

        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void updateEvent_eventNotFoundInSystem_negativeTest() {
        var expectedMessage = "Event not exists or id is empty";
        var eventIsSystem = Optional.empty();

        doReturn(eventIsSystem).when(eventRepository).findById(ID);

        var actualException = assertThrows(RuntimeException.class, () -> eventService.updateEvent(EVENT));

        assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void deleteEvent_eventFoundInSystem_test() {
        doReturn(Optional.of(EVENT)).when(eventRepository).findById(ID);

        var actualResult = eventService.deleteEvent(ID);

        assertEquals(Optional.of(EVENT), actualResult);
        verify(eventRepository, times(1)).deleteById(ID);
    }

    @Test
    public void deleteEvent_eventNotFoundInSystem_test() {
        var expectedResult = Optional.empty();

        doReturn(expectedResult).when(eventRepository).findById(ID);

        var actualResult = eventService.deleteEvent(ID);

        assertEquals(expectedResult, actualResult);
        verify(eventRepository, times(0)).deleteById(anyString());
    }

    @Test
    public void getEventById_test() {
        var expectedResult = Optional.of(EVENT);

        doReturn(expectedResult).when(eventRepository).findById(ID);

        var actualResult = eventService.getEventById(ID);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getEventsByTitle_test() {
        var expectedResult = List.of(
                Event.builder().id("1").title(TITLE).build(),
                Event.builder().id("2").title(TITLE).build(),
                Event.builder().id("3").title(TITLE).build()
        );

        doReturn(expectedResult).when(eventRepository).findEventsByTitle(TITLE);

        var actualResult = eventService.getEventsByTitle(TITLE);

        assertEquals(expectedResult, actualResult);
    }
}
