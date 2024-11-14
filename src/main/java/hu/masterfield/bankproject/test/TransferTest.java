package hu.masterfield.bankproject.test;

import hu.masterfield.bankproject.datatypes.Confirmation;
import hu.masterfield.bankproject.interfaces.AccountRepository;
import hu.masterfield.bankproject.interfaces.TransferService;
import hu.masterfield.bankproject.services.JdbcAccountRepository;
import hu.masterfield.bankproject.services.TransferServiceImpl;

public class TransferTest {
    public static void main(String[] args) {
        
        AccountRepository accRep = new JdbcAccountRepository();

        TransferService service = new TransferServiceImpl(accRep);

        Confirmation conf = service.transfer("Pénzes Péter", "Szegény Szilárd", 5000);
        System.out.print(conf);
    }
}
