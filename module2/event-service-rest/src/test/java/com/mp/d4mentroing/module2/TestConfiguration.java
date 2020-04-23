package com.mp.d4mentroing.module2;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.mp.d4mentroing.module2")
@EntityScan("com.mp.d4mentroing.module2")
@EnableAutoConfiguration()
public class TestConfiguration {
}
