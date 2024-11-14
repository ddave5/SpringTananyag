package hu.masterfield.bankproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import hu.masterfield.bankproject.configuration.XMLApplicationConfiguration;
import hu.masterfield.bankproject.datatypes.Confirmation;
import hu.masterfield.bankproject.interfaces.TransferService;

@SpringBootTest
@ContextConfiguration(classes=XMLApplicationConfiguration.class)
public class TransferServiceTests {

    @Autowired
    private TransferService service;

    @Test
    @Sql(scripts = "initbalance.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    public void transfer() {
        Confirmation receipt;

        receipt = service.transfer("Szegény Szilárd", "Pénzes Péter", 1000);

        System.out.println(receipt.getMessage());

        assertEquals(9000, receipt.getNewBalance());
    }
}
