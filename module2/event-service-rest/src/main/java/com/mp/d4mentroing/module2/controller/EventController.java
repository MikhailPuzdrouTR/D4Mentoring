package com.mp.d4mentroing.module2.controller;

import com.mp.d4mentroing.module2.domain.Event;
import com.mp.d4mentroing.module2.service.EventService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents(@RequestParam(value = "title", required = false) String title) {
        return title == null || title.isBlank() ? eventService.getAllEvents() : eventService.getEventsByTitle(title);
    }

    @GetMapping("/{eventId}")
    public Optional<Event> getEventById(@PathVariable("eventId") String eventId) {
        return eventService.getEventById(eventId);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent) {
        return new ResponseEntity<>(eventService.createEvent(newEvent), HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}")
    public Event updateEvent(@RequestBody Event event, @PathVariable("eventId") String eventId) {
        event.setId(eventId);
        return eventService.updateEvent(event);
    }

    @DeleteMapping("/{eventId}")
    public Optional<Event> deleteEventById(@PathVariable("eventId") String eventId) {
        return eventService.deleteEvent(eventId);
    }
}
