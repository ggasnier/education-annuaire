package com.guillaumegasnier.education.shell;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.guillaumegasnier.education"})
@EnableCaching
public class ShellApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShellApplication.class, args);
        System.exit(0);
    }

    @Bean
    public ApplicationRunner printVersion(BuildProperties buildProperties) {
        return args -> log.info("{} v{}", buildProperties.getName(), buildProperties.getVersion());
    }

}
