package hu.masterfield.bankproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import hu.masterfield.bankproject.configuration.XMLApplicationConfiguration;
import hu.masterfield.bankproject.datatypes.Confirmation;
import hu.masterfield.bankproject.interfaces.TransferService;

@SpringBootTest
@ContextConfiguration(classes=XMLApplicationConfiguration.class)
@TestPropertySource("/test_data.properties")
public class TransferServiceTests {

    private final int START_BALANCE = 10000;

    @Autowired
    private TransferService service;

    @Test
    @Sql(scripts = "initbalance.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    public void transfer(
        @Value("${test.fromAccountName}") String fromAccountName,
        @Value("${test.toAccountName}") String toAccountName,
        @Value("${test.amount}") int amount
        ) {
        Confirmation receipt;

        receipt = service.transfer(fromAccountName, toAccountName, amount);

        System.out.println(receipt.getMessage());

        assertEquals(START_BALANCE - amount, receipt.getNewBalance());
    }
}
