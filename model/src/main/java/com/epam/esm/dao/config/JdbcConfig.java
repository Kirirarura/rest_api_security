package com.epam.esm.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.epam.esm.entity")
@Profile("dev")
@EnableJpaRepositories("com.epam.esm.dao")
@EnableJpaAuditing
public class JdbcConfig {
}
