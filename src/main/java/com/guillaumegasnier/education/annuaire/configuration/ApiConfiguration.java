package com.guillaumegasnier.education.annuaire.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de l'Annuaire de l'Education et de la Formation en France")
                        .version("1.0.0")
                        .description("Cette API permet de gérer les établissements scolaires via leur UAI.")
                        .contact(new Contact()
                                .name("Guillaume GASNIER")
                                .url("https://guillaumegasnier.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(
                        List.of(new Server()
                                        .url("http://localhost:8080")
                                        .description("Serveur de développement"),
                                new Server()
                                        .url("https://annuaire.guillaumegasnier.com")
                                        .description("Serveur de test")
                        )
                );
    }
}
