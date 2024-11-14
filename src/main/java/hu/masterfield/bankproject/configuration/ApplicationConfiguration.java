package hu.masterfield.bankproject.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan({"hu.masterfield.bankproject.services", "hu.masterfield.bankproject.repository"})
@Import(InfraConfiguration.class)
public class ApplicationConfiguration {
    
}
