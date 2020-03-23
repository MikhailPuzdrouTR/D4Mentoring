package com.mp.d4mentroing.module2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="event")
public class Event {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "place")
    private String place;

    @Column(name = "speaker")
    private String speaker;

    @Column(name = "eventType")
    private EventType eventType;

    @Column(name = "dateTime")
    private Instant dateTime;
}
