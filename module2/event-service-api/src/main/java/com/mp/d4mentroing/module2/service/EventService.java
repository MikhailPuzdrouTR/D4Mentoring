package com.mp.d4mentroing.module2.service;

import com.mp.d4mentroing.module2.domain.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(Event newEvent);

    Event updateEvent(Event eventForUpdate);

    Optional<Event> deleteEvent(String deleteEventId);

    Optional<Event> getEventById(String eventId);

    List<Event> getAllEvents();

    List<Event> getEventsByTitle(String title);

}
