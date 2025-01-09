package ru.productstar.spring.jdbc1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.productstar.spring.jdbc1.dao.AccountDao;
import ru.productstar.spring.jdbc1.model.Account;

import java.util.List;

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        AccountDao accountDao = applicationContext.getBean(AccountDao.class);

        // 1 получение всех записей из таблицы
        List<Account> accountsOld = accountDao.getAllAccounts(); // List<Account
        log.info("All Accounts (before changes): {}", accountsOld);

        // 2 удаление всех записей из таблицы
        accountDao.deleteAllAccounts();
        log.info("All accounts deleted.");

        // 3 добавление записей в таблицу
        accountDao.addAccount(1L, 1000L);
        accountDao.addAccount(2L, 2000L);
        accountDao.addAccount(3L, 3000L);
        accountDao.addAccount(10L, 10000L);
        log.info("Accounts added: {}", accountDao.getAllAccounts());

        // 4 изменение записи ID=1 amount=5000
        accountDao.setAmount(1L, 5000L);
        log.info("Account ID=1 amount changed, current amount: {}", accountDao.getAccount(1L));

        // 5 получение всех записей из таблицы
        List<Account> accountsNew = accountDao.getAllAccounts(); // List<Account>
        log.info("All Accounts (after changes): {}", accountsNew);
    }
}