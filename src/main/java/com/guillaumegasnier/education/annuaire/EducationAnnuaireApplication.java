package com.guillaumegasnier.education.annuaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EducationAnnuaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationAnnuaireApplication.class, args);
    }

}
