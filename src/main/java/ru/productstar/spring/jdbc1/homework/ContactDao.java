package ru.productstar.spring.jdbc1.homework;

import java.util.List;

/**
 * Data Access Object for managing contacts in the database.
 */
public interface ContactDao {
    List<Contact> getAllContacts();

    Contact getContact(long contactId);

    long addContact(Contact contact);

    void updatePhoneNumber(long contactId, String phoneNumber);

    void updateEmail(long contactId, String email);

    void deleteContact(long contactId);
}