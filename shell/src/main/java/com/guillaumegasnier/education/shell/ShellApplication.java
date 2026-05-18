package com.guillaumegasnier.education.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.guillaumegasnier.education"})
@EnableCaching
public class ShellApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShellApplication.class, args);
        System.exit(0);
    }

}
