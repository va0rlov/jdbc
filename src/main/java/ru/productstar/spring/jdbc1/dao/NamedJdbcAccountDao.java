package ru.productstar.spring.jdbc1.dao;

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

    public NamedJdbcAccountDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public void addAccount(long id, long amount) {
        namedJdbcTemplate.update("INSERT INTO ACCOUNT(ID, AMOUNT) VALUES(:id, :amount)", new MapSqlParameterSource().addValue("id", id).addValue("amount", amount)
        );
    }

    @Override
    public Account getAccount(long accountId) {
        return namedJdbcTemplate.queryForObject(
                "SELECT ID, AMOUNT FROM ACCOUNT WHERE ID = :id",
                new MapSqlParameterSource("id", accountId),
                (rs, i) -> new Account(rs.getLong("ID"), rs.getLong("AMOUNT"))
        );
    }

    @Override
    public void setAmount(long accountId, long amount) {
        namedJdbcTemplate.update(
                "UPDATE ACCOUNT SET AMOUNT = :amount WHERE ID = :id",
                new MapSqlParameterSource()
                        .addValue("id", accountId)
                        .addValue("amount", amount)
        );
    }

    @Override
    public List<Account> getAllAccounts() {
        return namedJdbcTemplate.query(
                "SELECT ID, AMOUNT FROM ACCOUNT",
                (rs, i) -> new Account(rs.getLong("ID"), rs.getLong("AMOUNT"))
        );
    }

    @Override
    public void deleteAllAccounts() {
        namedJdbcTemplate.update("DELETE FROM ACCOUNT", Collections.emptyMap());
    }
}