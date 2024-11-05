package com.mdd.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

/**
 * Configuration class for application-wide beans and settings.
 */
@Configuration
public class AppConfig {

    /**
     * Creates and configures a ModelMapper bean for object mapping.
     * ModelMapper is used to map between DTOs and entity objects throughout the application.
     *
     * @return Configured ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
