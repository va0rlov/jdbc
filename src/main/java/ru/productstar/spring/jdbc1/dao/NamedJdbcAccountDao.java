package ru.productstar.spring.jdbc1.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.productstar.spring.jdbc1.model.Account;

import java.util.Collections;
import java.util.List;

@Repository
@Primary
public class NamedJdbcAccountDao implements AccountDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(NamedJdbcAccountDao.class);

    public NamedJdbcAccountDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public void addAccount(long id, long amount) {
        try {
            namedJdbcTemplate.update("INSERT INTO ACCOUNT(ID, AMOUNT) VALUES(:id, :amount)", new MapSqlParameterSource().addValue("id", id).addValue("amount", amount));
        } catch (Exception e) {
            logger.error("Ошибка при добавлении аккаунта с ID: {} и суммой: {}", id, amount, e);
        }
    }

    @Override
    public Account getAccount(long accountId) {
        try {
            return namedJdbcTemplate.queryForObject("SELECT ID, AMOUNT FROM ACCOUNT WHERE ID = :id", new MapSqlParameterSource("id", accountId), (rs, i) -> new Account(rs.getLong("ID"), rs.getLong("AMOUNT")));
        } catch (Exception e) {
            logger.error("Ошибка при получении аккаунта с ID: {}", accountId, e);
            return null; // Возвращаем null в случае ошибки
        }
    }

    @Override
    public void setAmount(long accountId, long amount) {
        try {
            namedJdbcTemplate.update("UPDATE ACCOUNT SET AMOUNT = :amount WHERE ID = :id", new MapSqlParameterSource().addValue("id", accountId).addValue("amount", amount));
        } catch (Exception e) {
            logger.error("Ошибка при обновлении суммы аккаунта с ID: {} на сумму: {}", accountId, amount, e);
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        try {
            return namedJdbcTemplate.query("SELECT ID, AMOUNT FROM ACCOUNT", (rs, i) -> new Account(rs.getLong("ID"), rs.getLong("AMOUNT")));
        } catch (Exception e) {
            logger.error("Ошибка при получении всех аккаунтов", e);
            return Collections.emptyList(); // Возвращаем пустой список в случае ошибки
        }
    }

    @Override
    public void deleteAllAccounts() {
        try {
            namedJdbcTemplate.update("DELETE FROM ACCOUNT", Collections.emptyMap());
        } catch (Exception e) {
            logger.error("Ошибка при удалении всех аккаунтов", e);
        }
    }
}