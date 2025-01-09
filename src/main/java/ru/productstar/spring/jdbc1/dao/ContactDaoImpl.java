package ru.productstar.spring.jdbc1.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.productstar.spring.jdbc1.model.Contact;

import java.util.List;
import java.util.Objects;

@Repository
public class ContactDaoImpl implements ContactDao {

    private static final Logger logger = LoggerFactory.getLogger(ContactDaoImpl.class);

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public ContactDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public List<Contact> getAllContacts() {
        logger.info("Fetching all contacts");
        return namedJdbcTemplate.query("SELECT id, name, surname, email, phone FROM contact", (rs, rowNum) -> new Contact(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getString("email"), rs.getString("phone")));
    }

    @Override
    public Contact getContact(long contactId) {
        logger.info("Fetching contact with id: {}", contactId);
        return namedJdbcTemplate.queryForObject("SELECT id, name, surname, email, phone FROM contact WHERE id = :id", new MapSqlParameterSource("id", contactId), (rs, rowNum) -> new Contact(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getString("email"), rs.getString("phone")));
    }

    @Override
    public long addContact(Contact contact) {
        logger.info("Adding new contact: {}", contact);
        var params = new MapSqlParameterSource().addValue("name", contact.getName()).addValue("surname", contact.getSurname()).addValue("email", contact.getEmail()).addValue("phone", contact.getPhone());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update("INSERT INTO contact (name, surname, email, phone) VALUES (:name, :surname, :email, :phone)", params, keyHolder, new String[]{"id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public void updatePhoneNumber(long contactId, String phoneNumber) {
        logger.info("Updating phone number for contact id: {}", contactId);
        namedJdbcTemplate.update("UPDATE contact SET phone = :phone WHERE id = :id", new MapSqlParameterSource().addValue("id", contactId).addValue("phone", phoneNumber));
    }

    @Override
    public void updateEmail(long contactId, String email) {
        logger.info("Updating email for contact id: {}", contactId);
        namedJdbcTemplate.update("UPDATE contact SET email = :email WHERE id = :id", new MapSqlParameterSource().addValue("id", contactId).addValue("email", email));
    }

    @Override
    public void deleteContact(long contactId) {
        logger.info("Deleting contact with id: {}", contactId);
        namedJdbcTemplate.update("DELETE FROM contact WHERE id = :id", new MapSqlParameterSource("id", contactId));
    }
}