package hu.masterfield.bankproject.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import hu.masterfield.bankproject.datatypes.Account;
import hu.masterfield.bankproject.interfaces.AccountRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class JdbcAccountRepository implements AccountRepository {

    @Autowired
    private DataSource ds;

    public JdbcAccountRepository(DataSource ds) {
        this.ds = ds;
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
    @Cacheable(value="accountCache", key="#accountNo")
    public Account loadAccount(String accountNo) throws SQLException {

        boolean isNewAccount = false;

        String sql =  "SELECT * FROM ACCOUNTS WHERE ACCOUNT_NAME = ?;";

        Account account = new Account();
        account.setAccountName(accountNo);

        try (PreparedStatement pst = ds.getConnection().prepareStatement(sql)) {
            pst.setString(1, accountNo.trim());
            System.out.println("Executing query: " + pst);

            ResultSet res = pst.executeQuery();

            if(res.next()) {
                account.setBalance(res.getInt("ACCOUNT_BALANCE"));
            } else {
                isNewAccount = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isNewAccount) insertAccount(account);

        return account;
    } 

    @Override
    public void updateAccount(Account account) throws SQLException {
        String sql =  "UPDATE ACCOUNTS SET ACCOUNT_BALANCE = ? WHERE ACCOUNT_NAME = ?;";

        try (PreparedStatement pst = ds.getConnection().prepareStatement(sql)) {
            pst.setInt(1, account.getBalance());
            pst.setString(2, account.getAccountName().trim());

            System.out.println("Executing query: " + pst);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertAccount(Account account) throws SQLException {
        String sql =  "INSERT INTO ACCOUNTS(ACCOUNT_NAME, ACCOUNT_BALANCE) VALUES(?, ?);";

        try (PreparedStatement pst = ds.getConnection().prepareStatement(sql)) {
            pst.setString(1, account.getAccountName().trim());
            pst.setInt(2, account.getBalance());

            System.out.println("Executing query: " + pst);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
