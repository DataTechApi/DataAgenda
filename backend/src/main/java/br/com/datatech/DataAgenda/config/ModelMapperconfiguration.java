package br.com.datatech.DataAgenda.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperconfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
