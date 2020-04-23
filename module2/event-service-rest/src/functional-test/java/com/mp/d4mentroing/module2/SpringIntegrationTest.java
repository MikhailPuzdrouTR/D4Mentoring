package com.mp.d4mentroing.module2;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mp.d4mentroing.module2.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;

@ContextConfiguration(classes = Runner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {

    private Logger logger = Logger.getLogger(SpringIntegrationTest.class.getName());

    @LocalServerPort
    protected int port;


    @Value("${'events-service.endpoints.events'}")
    private String eventsEndpoint;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected EventTestContext eventTestContext;

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected String getEventMappingUrl() throws MalformedURLException {
        return getEventMappingUrl("", Map.of());
    }

    protected String getEventMappingUrl(String subMapping) throws MalformedURLException {
        return getEventMappingUrl(subMapping, Map.of());
    }

    protected String getEventMappingUrl(String subMapping, Map<String, String> params) throws MalformedURLException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(new URL(String.format(eventsEndpoint, port, subMapping)).toString());

        for (String key : params.keySet()) {
            builder = builder.queryParam(key, params.get(key));
        }

        return builder.toUriString();
    }

    protected void saveEventResponseToContext(ResponseEntity<String> response) {
        eventTestContext.setHttpStatus(response.getStatusCode());
        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                eventTestContext.setRespondedEvent(objectMapper.readValue(response.getBody(), Event.class));
            } catch (IOException ex) {
                logger.warning("Error on parsing json: " + ex.getMessage());
            }
        }
    }
}
