package hu.masterfield.bankproject.interfaces;

import java.sql.SQLException;

import hu.masterfield.bankproject.datatypes.Account;

public interface AccountRepository {
    public Account loadAccount(String accountNo) throws SQLException;

    public void updateAccount(Account accountNo) throws SQLException;

    public void insertAccount(Account accountNo) throws SQLException;
}
