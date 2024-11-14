package hu.masterfield.bankproject.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import hu.masterfield.bankproject.datatypes.Account;
import hu.masterfield.bankproject.datatypes.Confirmation;
import hu.masterfield.bankproject.interfaces.AccountRepository;
import hu.masterfield.bankproject.interfaces.TransferService;

public class TransferServiceImpl implements TransferService {

    private AccountRepository accountRepository = null;

    public TransferServiceImpl() {
    }

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Confirmation transfer(String fromAccount, String toAccount, int amount) {
       Confirmation conf = new Confirmation();

       try {
        Account fromAcc = accountRepository.loadAccount(fromAccount);
        Account toAcc = accountRepository.loadAccount(toAccount);
        
        fromAcc.debit(amount);
        toAcc.credit(amount);

        accountRepository.updateAccount(fromAcc);
        accountRepository.updateAccount(toAcc);

        conf.setNewBalance(fromAcc.getBalance());
        conf.setMessage("Átutalás megtörtént: " + amount + " forint " + toAcc.getAccountName() + " számára. Új egyenleg: " + fromAcc.getBalance() + " Ft");
        conf.setSuccess(true);

       } catch (SQLException e) {
            conf.setMessage("Hiba történt az átutalás során. Hiba üzenet: " + e);
            conf.setSuccess(false);
       }

       return conf;
    }

    @Override
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
}
