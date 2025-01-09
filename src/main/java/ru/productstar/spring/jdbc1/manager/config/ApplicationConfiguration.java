package ru.productstar.spring.jdbc1.manager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.productstar.spring.jdbc1.common.JdbcConfiguration;

@Configuration
@ComponentScan("ru.productstar.spring.jdbc1.manager")
@Import(JdbcConfiguration.class) // Импортируем конфигурацию JdbcConfig
public class ApplicationConfiguration {
}