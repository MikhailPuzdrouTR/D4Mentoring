package com.mp.d4mentroing.module2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.mp.d4mentroing.module2"})
@EnableJpaRepositories("com.mp.d4mentroing.module2")
@EntityScan("com.mp.d4mentroing.module2")
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
}
