package ru.productstar.spring.jdbc1.dao;

import ru.productstar.spring.jdbc1.model.Account;

import java.util.List;

public interface AccountDao {
    default void addAccount(long id, long amount) {
    }

    Account getAccount(long accountId);

    void setAmount(long accountId, long amount);

    List<Account> getAllAccounts();

    void deleteAllAccounts(); // Новый метод для удаления всех записей
}
