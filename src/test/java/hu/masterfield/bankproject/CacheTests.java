package hu.masterfield.bankproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import hu.masterfield.bankproject.configuration.XMLApplicationConfiguration;
import hu.masterfield.bankproject.datatypes.Confirmation;
import hu.masterfield.bankproject.interfaces.TransferService;

public class CacheTests {
    private TransferService service;

    @BeforeEach
    public void setUp() {
        ApplicationContext context = SpringApplication.run(XMLApplicationConfiguration.class);

        service = (TransferService) context.getBean("transferService");
    }

    @Test
    public void transfer() {
        Confirmation receipt;

        receipt = service.transfer("Szegény Szilárd", "Pénzes Péter", 1000);

        System.out.println(receipt.getMessage());

        receipt = service.transfer("Szegény Szilárd", "Pénzes Péter", 2000);

        System.out.println(receipt.getMessage());
    }
}
