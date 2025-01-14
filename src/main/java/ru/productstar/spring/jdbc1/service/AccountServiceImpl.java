package ru.productstar.spring.jdbc1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.productstar.spring.jdbc1.dao.AccountDao;
import ru.productstar.spring.jdbc1.model.Account;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountDao.getAllAccounts();
        log.info("All Accounts (before changes): {}", accounts);
        return accounts;
    }

    @Override
    public void deleteAllAccounts() {
        accountDao.deleteAllAccounts();
        log.info("All accounts deleted.");
    }

    @Override
    public void addAccounts() {
        accountDao.addAccount(1L, 1000L);
        accountDao.addAccount(2L, 2000L);
        accountDao.addAccount(3L, 3000L);
        accountDao.addAccount(10L, 10000L);
        log.info("Accounts added: {}", accountDao.getAllAccounts());
    }

    @Override
    public void updateAccount(long accountId, long amount) {
        accountDao.setAmount(accountId, amount);
        log.info("Account ID={} amount changed, current amount: {}", accountId, accountDao.getAccount(accountId));
    }

    @Override
    public List<Account> getAccountsAfterChanges() {
        List<Account> accounts = accountDao.getAllAccounts();
        log.info("All Accounts (after changes): {}", accounts);
        return accounts;
    }
}