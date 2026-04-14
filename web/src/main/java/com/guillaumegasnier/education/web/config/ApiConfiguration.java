package com.guillaumegasnier.education.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApiConfiguration {

    private static final String API_KEY_SCHEME = "ApiKeyAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de l'Annuaire de l'Education et de la Formation en France")
                        .version("1.0.1")
                        .description("Cette API permet de gérer les établissements scolaires via leur UAI.")
                        .contact(new Contact()
                                .name("Guillaume GASNIER")
                                .url("https://formakoi.guillaumegasnier.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(
                        List.of(new Server()
                                        .url("http://localhost:8080")
                                        .description("Serveur de développement"),
                                new Server()
                                        .url("https://formakoi.guillaumegasnier.com")
                                        .description("Serveur de prod")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList(API_KEY_SCHEME))
                .schemaRequirement(API_KEY_SCHEME, new SecurityScheme()
                        .name(ApiKeyFilter.API_KEY_HEADER)
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .description("Clef d'API à fournir pour les endpoints non-GET (header X-API-Key)")
                );
    }
}


