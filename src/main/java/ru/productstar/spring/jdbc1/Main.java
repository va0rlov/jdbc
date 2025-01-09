package ru.productstar.spring.jdbc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.productstar.spring.jdbc1.dao.AccountDao;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        AccountDao accountDao = applicationContext.getBean(AccountDao.class);

        var account = accountDao.getAccount(1L);
        System.out.println(account);

        accountDao.setAmount(1L, 2000L);
        account = accountDao.getAccount(1L);
        System.out.println(account);

        var newAccount = accountDao.addAccount(10L, 10000L);
        System.out.println(newAccount);

        var accounts = accountDao.getAllAccounts();
        System.out.println(accounts);
    }
}