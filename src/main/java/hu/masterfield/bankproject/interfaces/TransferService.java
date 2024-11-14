package hu.masterfield.bankproject.interfaces;

import hu.masterfield.bankproject.datatypes.Confirmation;

public interface TransferService {
    public Confirmation transfer(String fromAccount, String toAccount, int amount);

    public void setAccountRepository(AccountRepository accountRepository);
}
