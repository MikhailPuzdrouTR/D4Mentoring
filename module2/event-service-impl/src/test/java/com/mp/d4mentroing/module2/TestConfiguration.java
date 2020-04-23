package com.mp.d4mentroing.module2;

import com.mp.d4mentroing.module2.repository.EventRepository;
import com.mp.d4mentroing.module2.service.EventService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

@EnableJpaRepositories("com.mp.d4mentroing.module2")
@EntityScan("com.mp.d4mentroing.module2")
@ContextConfiguration(classes = {EventService.class, EventRepository.class})
@SpringBootConfiguration
public class TestConfiguration {
}
