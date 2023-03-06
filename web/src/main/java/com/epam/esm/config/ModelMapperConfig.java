package com.epam.esm.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.Instant;
import java.time.ZonedDateTime;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(Converter<Instant, ZonedDateTime> instantZonedDateTimeConverter) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        modelMapper.typeMap(Instant.class, ZonedDateTime.class)
                .setConverter(instantZonedDateTimeConverter);

        return modelMapper;
    }

    @Bean
    public Converter<Instant, ZonedDateTime> instantZonedDateTimeConverter() {
        return mappingContext -> mappingContext.getSource()
                .atZone(LocaleContextHolder.getTimeZone().toZoneId());
    }
}