package ru.productstar.spring.jdbc1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.productstar.spring.jdbc1.service.AccountService;
import ru.productstar.spring.jdbc1.service.ContactService;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        // Запуск работы с аккаунтами
        if (args.length > 0 && "accounts".equals(args[0])) {
            runAccountService(applicationContext);
        }

        // Запуск работы с контактами
        if (args.length > 0 && "contacts".equals(args[0])) {
            runContactService(applicationContext);
        }
    }

    private static void runAccountService(ConfigurableApplicationContext context) {
        AccountService accountService = context.getBean(AccountService.class);

        // 1. Получение всех записей из таблицы
        accountService.getAllAccounts();

        // 2. Удаление всех записей из таблицы
        accountService.deleteAllAccounts();

        // 3. Добавление записей в таблицу
        accountService.addAccounts();

        // 4. Изменение записи ID=1 amount=5000
        accountService.updateAccount(1L, 5000L);

        // 5. Получение всех записей из таблицы
        accountService.getAccountsAfterChanges();
    }

    private static void runContactService(ConfigurableApplicationContext context) {
        ContactService contactService = context.getBean(ContactService.class);

        try (FileInputStream inputStream = new FileInputStream("C:\\Users\\VOrlov\\Code\\jdbc1\\src\\main\\resources\\contacts.csv")) {
            contactService.importContactsFromCsv(inputStream);
            log.info("Contacts imported successfully.");
        } catch (IOException e) {
            log.error("Error reading CSV file", e);
        }
    }
}