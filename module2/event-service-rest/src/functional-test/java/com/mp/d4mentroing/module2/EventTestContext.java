package com.mp.d4mentroing.module2;

import com.mp.d4mentroing.module2.domain.Event;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class EventTestContext {

    private HttpStatus httpStatus;
    private Event respondedEvent;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Event getRespondedEvent() {
        return respondedEvent;
    }

    public void setRespondedEvent(Event respondedEvent) {
        this.respondedEvent = respondedEvent;
    }
}
