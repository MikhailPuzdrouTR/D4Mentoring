package com.mp.d4mentroing.module2.repository;

import com.mp.d4mentroing.module2.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {

    List<Event> findEventsByTitle(String title);
}
