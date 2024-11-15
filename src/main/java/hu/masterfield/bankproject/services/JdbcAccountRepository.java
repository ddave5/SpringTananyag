package hu.masterfield.bankproject.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import hu.masterfield.bankproject.datatypes.Account;
import hu.masterfield.bankproject.interfaces.AccountRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class JdbcAccountRepository implements AccountRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public JdbcAccountRepository(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
        dumpAllAccounts();
        dumpAllAccountsWithMapper();
        dumpAllAccountsWithLambda();
    }

    public JdbcAccountRepository() {
    }

    @PostConstruct
    void startLogging() {
        System.out.println("logging START");
    }

    @PreDestroy
    void stopLogging() {
        System.out.println("logging STOP");
    }

    @Override
    public Account loadAccount(String accountNo) throws SQLException {
        Account account = new Account();
        account.setAccountName(accountNo);

        try {
            Integer res = (Integer) jdbcTemplate
                                    .queryForMap( "SELECT * FROM ACCOUNTS WHERE ACCOUNT_NAME = ?;", accountNo.trim()).get("ACCOUNT_BALANCE");
            account.setBalance(res);
        } catch (EmptyResultDataAccessException e) {
            insertAccount(account);
        }

        return account;
    } 

    @Override
    public void updateAccount(Account account) throws SQLException {
        jdbcTemplate
            .update("UPDATE ACCOUNTS SET ACCOUNT_BALANCE = ? WHERE ACCOUNT_NAME = ?;", 
                        account.getBalance(), 
                        account.getAccountName());

    }

    @Override
    public void insertAccount(Account account) throws SQLException {
        jdbcTemplate
            .update("INSERT INTO ACCOUNTS(ACCOUNT_NAME, ACCOUNT_BALANCE) VALUES(?, ?);", 
                        account.getAccountName(),
                        account.getBalance());
    }

    private void dumpAllAccounts() {
        List<Map<String, Object>> records = jdbcTemplate.queryForList("SELECT * FROM ACCOUNTS");

        System.out.println("------------ DUMP ------------");
        for (Map<String, Object> rec : records) {
            System.out.println(rec);
        }
    }

    private void dumpAllAccountsWithMapper() {
        List<Account> records = jdbcTemplate.query("SELECT * FROM ACCOUNTS", new AccountMapper());

        System.out.println("------------ DUMP ------------");
        records.forEach(record -> System.out.println(record));
    }

    private void dumpAllAccountsWithLambda() {
        List<Account> records = jdbcTemplate.query("SELECT * FROM ACCOUNTS", (rs, rowNum) -> {
            return new Account(rs.getString("ACCOUNT_NAME"), rs.getInt("ACCOUNT_BALANCE"));
        });

        System.out.println("------------ DUMP ------------");
        records.forEach(record -> System.out.println(record));
    }

}
