package ru.productstar.spring.jdbc1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.productstar.spring.jdbc1.dao.ContactDao;
import ru.productstar.spring.jdbc1.model.Contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final ContactDao contactDao;

    @Autowired
    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    @Transactional
    public void importContactsFromCsv(InputStream inputStream) throws IOException {
        List<Contact> contacts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Убираем лишние пробелы и разделяем строку по запятым
                String[] values = line.trim().split("\\s*,\\s*");

                // Проверяем, что строка содержит 4 значения: Имя, Фамилия, Номер телефона, Email
                if (values.length == 4) {
                    String name = values[0].trim();
                    String surname = values[1].trim();
                    String phone = values[2].trim();
                    String email = values[3].trim();

                    // Создаем объект Contact и добавляем его в список
                    Contact contact = new Contact(name, surname, email, phone);
                    contacts.add(contact);
                } else {
                    logger.warn("Skipping invalid line: {}", line);
                }
            }
        }

        // Batch insert contacts
        if (!contacts.isEmpty()) {
            for (Contact contact : contacts) {
                contactDao.addContact(contact);
            }
            logger.info("Imported {} contacts from CSV file.", contacts.size());
        } else {
            logger.warn("No contacts were imported. CSV file might be empty or invalid.");
        }
    }
}