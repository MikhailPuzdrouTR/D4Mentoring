package com.mp.d4mentroing.module2.serviceimpl;

import com.mp.d4mentroing.module2.service.EventService;
import com.mp.d4mentroing.module2.domain.Event;
import com.mp.d4mentroing.module2.repository.EventRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event newEvent) {
        newEvent.setId(UUID.randomUUID().toString());
        return eventRepository.save(newEvent);
    }

    @Override
    public Event updateEvent(Event eventForUpdate) {
        if (eventForUpdate.getId() == null || getEventById(eventForUpdate.getId()).isEmpty()) {
            throw new RuntimeException("Event not exists or id is empty");
        }
        return eventRepository.save(eventForUpdate);
    }

    @Override
    public Optional<Event> deleteEvent(String deleteEventId) {
        Optional<Event> foundEvent = getEventById(deleteEventId);
        foundEvent.ifPresent(event -> eventRepository.deleteById(deleteEventId));
        return foundEvent;
    }

    @Override
    public Optional<Event> getEventById(String eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        return eventRepository.findEventsByTitle(title);
    }
}
