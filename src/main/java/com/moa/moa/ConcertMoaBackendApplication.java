package com.moa.moa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConcertMoaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcertMoaBackendApplication.class, args);
    }

}
