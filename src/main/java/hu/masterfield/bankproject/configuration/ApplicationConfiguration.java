package hu.masterfield.bankproject.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import hu.masterfield.bankproject.interfaces.AccountRepository;
import hu.masterfield.bankproject.interfaces.TransferService;
import hu.masterfield.bankproject.services.JdbcAccountRepository;
import hu.masterfield.bankproject.services.TransferServiceImpl;


@Configuration
@ComponentScan({"hu.masterfield.bankproject.services", "hu.masterfield.bankproject.repository"})
@Import(InfraConfiguration.class)
public class ApplicationConfiguration {

    @Autowired
    DataSource ds;
    
    @Bean
    public TransferService transferService() {
        return new TransferServiceImpl(accountRepository());
    }

    @Bean
    public AccountRepository accountRepository() {
        return new JdbcAccountRepository(ds);
    }

    
}
