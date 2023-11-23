package com.formation.film;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmApplication.class, args);
    }

}
