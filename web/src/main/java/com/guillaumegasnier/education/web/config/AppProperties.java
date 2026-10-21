package com.guillaumegasnier.education.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("app.properties")
public class AppProperties {

    private String title;

    private String apiKey;
}
