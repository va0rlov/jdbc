package ru.productstar.spring.jdbc1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("ru.productstar.spring.jdbc1.dao")
@Import(JdbcConfiguration.class) // Импортируем конфигурацию JdbcConfig
public class ApplicationConfiguration {
}