package ru.productstar.spring.jdbc1.dao;

import ru.productstar.spring.jdbc1.model.Account;

import java.util.List;

public interface AccountDao {
    Account addAccount(long id, long amount);

    Account addAccount(long amount);

    Account getAccount(long accountId);

    void setAmount(long accountId, long amount);

    List<Account> getAllAccounts();
}
