package org.treinamento.config;

import org.springframework.cloud.openfeign.support.JsonFormWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    JsonFormWriter jsonFormWriter() {
        return new JsonFormWriter();
    }
}
