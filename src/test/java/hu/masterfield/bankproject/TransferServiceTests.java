package hu.masterfield.bankproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import hu.masterfield.bankproject.configuration.ApplicationConfiguration;
import hu.masterfield.bankproject.datatypes.Confirmation;
import hu.masterfield.bankproject.interfaces.TransferService;

public class TransferServiceTests {
    private TransferService service;

    @BeforeEach
    public void setUp() {
        ApplicationContext context = SpringApplication.run(ApplicationConfiguration.class);

        service = (TransferService) context.getBean("transferService");
    }

    @Test
    public void transfer() {
        Confirmation receipt;

        receipt = service.transfer("Szegény Szilárd", "Pénzes Péter", 1000);

        System.out.println(receipt.getMessage());

        assertEquals(9000, receipt.getNewBalance());
    }
}
