package org.example.phd_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PhdBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhdBookApplication.class, args);
    }
}
