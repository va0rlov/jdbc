package ru.productstar.spring.jdbc1.dao;

import ru.productstar.spring.jdbc1.model.Contact;

import java.util.List;

public interface ContactDao {
    List<Contact> getAllContacts();

    Contact getContact(long contactId);

    long addContact(Contact contact);

    void addContactsBatch(List<Contact> contacts);

    boolean contactExists(String email, String phone); // Новый метод для проверки дубликатов

    void updatePhoneNumber(long contactId, String phoneNumber);

    void updateEmail(long contactId, String email);

    void deleteContact(long contactId);
}