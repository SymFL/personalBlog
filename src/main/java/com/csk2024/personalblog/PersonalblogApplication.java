package com.csk2024.personalblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Transactional
public class PersonalblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalblogApplication.class, args);
    }

}
