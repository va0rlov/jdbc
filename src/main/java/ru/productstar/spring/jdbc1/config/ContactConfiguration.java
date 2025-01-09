package ru.productstar.spring.jdbc1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.productstar.spring.jdbc1.dao.ContactDao;
import ru.productstar.spring.jdbc1.dao.ContactDaoImpl;

@Configuration
@Import({JdbcConfiguration.class, PropertiesConfiguration.class})
public class ContactConfiguration {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ContactConfiguration(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Bean
    public ContactDao contactDao() {
        return new ContactDaoImpl(namedParameterJdbcTemplate);
    }
}