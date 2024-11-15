package hu.masterfield.bankproject.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hu.masterfield.bankproject.datatypes.Account;

public class AccountMapper implements RowMapper<Account>{

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Account(rs.getString("ACCOUNT_NAME"), rs.getInt("ACCOUNT_BALANCE"));
    }
    
}
