package hu.masterfield.bankproject.datatypes;

public class Account {
    private String accountName = null;
    private int balance = 10000;

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
