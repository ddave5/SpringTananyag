package hu.masterfield.bankproject.datatypes;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="ACCOUNTS")
public class Account implements Serializable{

    public static final int DEFAULT_BALANCE = 10000;

    @Id
    @Column(name = "ACCOUNT_NAME")
    private String accountName = null;

    @Column(name = "ACCOUNT_BALANCE")
    private int balance = DEFAULT_BALANCE;

    @Transient
    private String RSAsecret = null;

    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public Account(String accountName, int balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Confirmation [accountName = " + accountName + ", balance=" + balance + "]";
    }

    public void debit(int amount) {
        this.balance -= amount;
    }

    public void credit (int amount) {
        this.balance += amount;
    }


     
}
