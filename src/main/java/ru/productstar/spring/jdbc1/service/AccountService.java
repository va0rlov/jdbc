package ru.productstar.spring.jdbc1.service;

import ru.productstar.spring.jdbc1.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();

    void deleteAllAccounts();

    void addAccounts();

    void updateAccount(long accountId, long amount);

    List<Account> getAccountsAfterChanges();
}