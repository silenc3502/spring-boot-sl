package com.example.springspeciallecture.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.example.springspeciallecture.reactive_board.repository")
public class R2dbcConfig {
}
