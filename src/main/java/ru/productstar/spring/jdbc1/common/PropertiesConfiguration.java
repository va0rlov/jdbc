package ru.productstar.spring.jdbc1.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class PropertiesConfiguration {
}
