package ru.productstar.spring.jdbc1.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.productstar.spring.jdbc1.model.Account;

import java.util.List;

@Repository
public class JdbcAccountDao implements AccountDao {

    private static final String GET_ACCOUNT_SQL = "SELECT ID, AMOUNT FROM ACCOUNT WHERE ID = ?";
    private static final String SET_AMOUNT_SQL = "UPDATE ACCOUNT SET AMOUNT = ? WHERE ID = ?";
    private static final String GET_ALL_ACCOUNTS_SQL = "SELECT ID, AMOUNT FROM ACCOUNT";

    private static final RowMapper<Account> ACCOUNT_ROW_MAPPER = (rs, i) -> new Account(rs.getLong("ID"), rs.getLong("AMOUNT"));

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAccount(long id, long amount) {
        jdbcTemplate.update("INSERT INTO ACCOUNT(ID, AMOUNT) VALUES(?, ?)", id, amount);
    }

    @Override
    public Account getAccount(long accountId) {
        return jdbcTemplate.queryForObject(GET_ACCOUNT_SQL, ACCOUNT_ROW_MAPPER, accountId);
    }

    @Override
    public void setAmount(long accountId, long amount) {
        jdbcTemplate.update(SET_AMOUNT_SQL, amount, accountId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return jdbcTemplate.query(GET_ALL_ACCOUNTS_SQL, ACCOUNT_ROW_MAPPER);
    }

    @Override
    public void deleteAllAccounts() {
        jdbcTemplate.update("DELETE FROM ACCOUNT");
    }
}